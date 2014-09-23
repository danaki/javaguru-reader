'use strict';

javaguruReaderApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/feed', {
                    templateUrl: 'views/feeds.html',
                    controller: 'FeedController',
                    resolve:{
                        resolvedFeed: ['Feed', function (Feed) {
                            return Feed.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
