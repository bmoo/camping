(function() {
    var app = angular.module("monitor", ['ui.bootstrap', 'ui.directives']);

    app.controller("SiteDefinitionController", function ($scope) {
        $scope.newDefinitionForm = {};
        $scope.newDefinitionForm.URL = "http://";
    });


})();