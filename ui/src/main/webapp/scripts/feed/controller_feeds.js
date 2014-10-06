'use strict';

javaguruReaderApp.controller('FeedsController', function ($scope, $location, resolvedFeeds, Feed) {

    $scope.feeds = resolvedFeeds;

    $scope.goDetails = function (id) {
        $location.path('/feed/' + id);
    };

    $scope.create = function () {
        Feed.save($scope.feed,
            function () {
                $scope.feeds = Feed.query();
                $('#saveFeedModal').modal('hide');
                $scope.clear();
            });
    };

    $scope.update = function (id) {
        $scope.feed = Feed.get({id: id});
        $('#saveFeedModal').modal('show');
    };

    $scope.delete = function (id) {
        Feed.delete({id: id},
            function () {
                $scope.feeds = Feed.query();
            });
    };

    $scope.clear = function () {
        $scope.feed = {id: null, url: null, entriesUpdatedAt: null};
    };
});
