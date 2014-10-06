'use strict';

javaguruReaderApp.factory('Entry', ['$resource', 'REST_BASE', function ($resource, REST_BASE) {
    return $resource(REST_BASE.url + '/entries/:id', {}, {
        'query': { method: 'GET', isArray: true, transformResponse: JSOG.parse },
        'get': { method: 'GET', transformResponse: JSOG.parse }
    });
}]);
