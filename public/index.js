(function() {
    var app = angular.module("monitor", []);

    app.controller("SiteDefinitionController", function ($scope) {
            $scope.newDefinitionForm = {};
            $scope.newDefinitionForm.URL = "http://";
        });


})();