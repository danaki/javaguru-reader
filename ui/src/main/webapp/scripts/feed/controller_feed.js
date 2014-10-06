'use strict';

javaguruReaderApp.controller('FeedController', function ($scope, $location, resolvedFeed, Feed) {
    $scope.feed = resolvedFeed;
});
