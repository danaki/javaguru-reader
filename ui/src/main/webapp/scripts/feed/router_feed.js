'use strict';

javaguruReaderApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/feeds/:id', {
                    templateUrl: 'views/feed.html',
                    controller: 'FeedController',
                    resolve: {
                        resolvedFeed: ['Feed', '$route', function (Feed, $route) {
                            return Feed.get({id: $route.current.params.id});
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
