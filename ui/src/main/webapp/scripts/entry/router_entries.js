'use strict';

javaguruReaderApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/entries', {
                    templateUrl: 'views/entries.html',
                    controller: 'EntriesController',
                    resolve: {
                        resolvedEntries: ['Entry', function (Entry) {
                            return Entry.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
