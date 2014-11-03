//
//  CampSiteDefinitionTableViewController.swift
//  Camping
//
//  Created by Brad Moore on 8/10/14.
//  Copyright (c) 2014 Brad Moore. All rights reserved.
//

import Foundation
import UIKit

class CampSiteDefinitionTableViewController: UITableViewController {
    let cellReuseIdentifier = "SiteDefinitionCell"
    let baseUrl = NSURL(string: "http://localhost:8080")
    
    var siteDefinitions: [String]!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        siteDefinitions = ["test"]
    }
    
    func fetchCampSiteDefinitions() -> [String] {
        
        let url = NSURL(string: "/campSiteDefinition", relativeToURL: baseUrl)
        let request = NSURLRequest(URL: url)
        
        NSURLConnection.sendAsynchronousRequest(request, queue: NSOperationQueue.mainQueue(), completionHandler: {response, data, error in
            
            if (!error && data.length > 0) {
                println("got here")
                
                var serializationError: NSError?
                let dataAsDictionary: NSDictionary = NSJSONSerialization.JSONObjectWithData(data, options: nil, error: &serializationError) as NSDictionary
                
                let embedded: NSDictionary = dataAsDictionary["_embedded"] as NSDictionary
                let campSiteDefinitions = embedded["campSiteDefinitions"] as NSArray
                
                let firstCampSite = campSiteDefinitions[0] as NSDictionary
                let displayName = firstCampSite["displayName"] as String
                
                println("Got displayName \(displayName)")
            } else {
                NSLog("Error - \(error.description)")
            }
        })
        
        return ["test"]
        
    }
    
    
    // #pragma mark - Table view data source
    override func numberOfSectionsInTableView(tableView: UITableView?) -> Int {
        fetchCampSiteDefinitions()
        
        return siteDefinitions.count
    }
    
    override func tableView(tableView: UITableView?, numberOfRowsInSection section: Int) -> Int {
        return self.siteDefinitions.count
    }
    
    
    override func tableView(tableView: UITableView?, cellForRowAtIndexPath indexPath: NSIndexPath?) -> UITableViewCell? {
        let cell: UITableViewCell = UITableViewCell(style: UITableViewCellStyle.Default, reuseIdentifier: cellReuseIdentifier)
        
        cell.textLabel.text = siteDefinitions[indexPath!.row]
        
        return cell
    }
}