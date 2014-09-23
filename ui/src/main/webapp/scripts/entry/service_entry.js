'use strict';

javaguruReaderApp.factory('Entry', function ($resource) {
        return $resource('app/rest/entrys/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
