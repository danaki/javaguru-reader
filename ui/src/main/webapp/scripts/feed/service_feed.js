'use strict';

javaguruReaderApp.factory('Feed', function ($resource) {
        return $resource('app/rest/feeds/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
