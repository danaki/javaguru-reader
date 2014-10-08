'use strict';

javaguruReaderApp.factory('Feed', ['$resource', 'REST_BASE', function ($resource, REST_BASE) {
    return $resource(REST_BASE.url + '/feeds/:id', { id: '@id' }, {
        'query': { method: 'GET', isArray: true, transformResponse: JSOG.parse },
        'get': { method: 'GET', transformResponse: JSOG.parse },
        'refresh': { method: 'PUT' }
    });
}]);
