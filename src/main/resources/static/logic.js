var app = angular.module('app', ['ngRoute']);
var contextPath = 'http://localhost:8189/rest/store'

app.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'about-page.html',
            controller: 'aboutController'
        })
        .when('/books', {
            templateUrl: 'store-page.html',
            controller: 'booksController'
        })
});

app.controller('aboutController', function ($scope, $http) {
    fillTable = function () {
        $http.get(contextPath + '/api/v1/books/dtos')
            .then(function (response) {
                $scope.PopularBooksList = response.data;
            });
    }
    fillTable();
});

app.controller('booksController', function ($scope, $http) {
    fillTable = function (p, filterParams) {
        var url = contextPath + '/api/v1/books/show'
        if(p != null) url = url +'?p=' + p;
        if(filterParams != null && !angular.isString(filterParams)) {
            if(filterParams.minPrice != null)
            url = url +'&minPrice=' + filterParams.minPrice;
            if(filterParams.maxPrice != null)
            url = url +'&maxPrice=' + filterParams.maxPrice;
            if(filterParams.titlePart != null)
            url = url +'&titlePart=' + filterParams.titlePart;
            angular.forEach($scope.filterBook.gen, function(value, key) {
            if(value)
                url = url + '&gen='+key;
            });
        }
        if(angular.isString(filterParams)) url = url + filterParams;

        $http.get(url)
            .then(function (response) {
                $scope.BooksList = response.data.books;
                $scope.page = response.data.page;
                $scope.pageCount = response.data.pageCount;
                $scope.arrayPage = response.data.arrayPage;
                $scope.allGenres = response.data.allGenres;
                $scope.filterParams = response.data.filterParams;
            });
    };

    $scope.query = function(p, filterParams) {
            $scope.p = p;
            fillTable(p, filterParams);
            console.log(p)
            console.log(filterParams)
        };

    $scope.submitFilter = function () {
        fillTable($scope.page, $scope.filterBook);
        console.log($scope.page)
        console.log($scope.filterBook)
    };

    fillTable();
});