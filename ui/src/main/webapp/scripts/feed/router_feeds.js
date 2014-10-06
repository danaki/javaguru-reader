'use strict';

javaguruReaderApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/feeds', {
                    templateUrl: 'views/feeds.html',
                    controller: 'FeedsController',
                    resolve: {
                        resolvedFeeds: ['Feed', function (Feed) {
                            return Feed.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
