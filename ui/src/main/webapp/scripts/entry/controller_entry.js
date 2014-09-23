'use strict';

javaguruReaderApp.controller('EntryController', function ($scope, resolvedEntry, Entry) {

        $scope.entrys = resolvedEntry;

        $scope.create = function () {
            Entry.save($scope.entry,
                function () {
                    $scope.entrys = Entry.query();
                    $('#saveEntryModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.entry = Entry.get({id: id});
            $('#saveEntryModal').modal('show');
        };

        $scope.delete = function (id) {
            Entry.delete({id: id},
                function () {
                    $scope.entrys = Entry.query();
                });
        };

        $scope.clear = function () {
            $scope.entry = {id: null, feed: null, url: null, title: null, createdAt: null};
        };
    });
