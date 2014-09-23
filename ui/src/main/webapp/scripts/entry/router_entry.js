'use strict';

javaguruReaderApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/entry', {
                    templateUrl: 'views/entrys.html',
                    controller: 'EntryController',
                    resolve:{
                        resolvedEntry: ['Entry', function (Entry) {
                            return Entry.query();
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
