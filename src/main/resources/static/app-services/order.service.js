﻿(function () {
    'use strict';

    angular
        .module('app')
        .factory('OrderService', OrderService);

    OrderService.$inject = ['$http', '$translate', '$translatePartialLoader'];
    function OrderService($http, $translate, $translatePartialLoader) {
        var service = {},
            _translatePartialLoader = $translatePartialLoader,
            _translate = $translate;

        _translatePartialLoader.addPart('system');
        _translatePartialLoader.addPart('exception');
        _translatePartialLoader.addPart('order');
        _translate.refresh();

        service.finalizeCurrentFromUser = finalizeCurrentFromUser;

        return service;

        function finalizeCurrentFromUser() {
            return $http.post('/api/v1/orders').then(handleSuccess, handleError);
        }

        // private functions

        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            var errorCode = error.data && error.data.message ? error.data.message : 'system.operationError',
                errorMessage = _translate.instant(errorCode);

            return { success: false, message: errorMessage };
        }
    }

})();
