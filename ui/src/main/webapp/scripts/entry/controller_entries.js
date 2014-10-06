'use strict';

javaguruReaderApp.controller('EntriesController', function ($scope, resolvedEntries, Entry) {
    $scope.entries = resolvedEntries;

    $scope.clear = function () {
        $scope.entry = {id: null, feed: null, url: null, title: null, createdAt: null};
    };
});
