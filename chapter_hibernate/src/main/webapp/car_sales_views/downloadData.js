//отправляет регистрационные данные на сервер
function registration() {
    var name = $('#name').val();
    var pass = $('#pass').val();
    var data = 'name=' + name + '&pass=' + pass;
    $.ajax({
        type: 'post',
        url: 'http://localhost:8080/hibernate/getdata',
        data: data,
        dataType: 'text',
        error: [function(xhr, status, error) {
            alert(xhr.responseText + '|\n' + status + '|\n' + error);
        }],
        success: [function (response) {
            $('#message').html(response);
        }]
    })
}

function fillTable(response) {
    $.each(response, function () {
        var year = 0, month = 0, day = 0;
        if (this.data !== null) {
            var curData = new Date(this.data);
            year = curData.getFullYear();
            month = curData.getMonth();
            day = curData.getDate();
        }
        $('#cars_table').append('<tr><td><img src="' + this.picturePath + '"></td>'
            + '<td>Brand: ' + this.model.brand.name
            + '<br>Model: ' + this.model.name
            + '<br>Color: ' + this.color
            + '<br>Body type: ' + this.bodyType.name
            + '<br>Year of issue: ' + this.year
            + '<br>Seller: ' + this.seller.name
            + '<br>Application date: ' + year + '-' + month + '-' + day
            + '<br>Status: ' + this.status
            + '</td></tr>');
    });
}

function getAllCars() {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/hibernate/cars',
        dataType: 'json',
        error: [function(xhr, status, error) {
            alert(xhr.responseText + '|\n' + status + '|\n' + error);
        }],
        success: [function (response) {
            fillTable(response);
        }]
    });
}

function getOnlyFotoCars() {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/hibernate/cars',
        data: 'onlyFoto=' + true,
        dataType: 'json',
        error: [function(xhr, status, error) {
            alert(xhr.responseText + '|\n' + status + '|\n' + error);
        }],
        success: [function (response) {
            fillTable(response);
        }]
    });
}

function getCurrentDataCars() {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/hibernate/cars',
        data: 'currentData=' + true,
        dataType: 'json',
        error: [function(xhr, status, error) {
            alert(xhr.responseText + '|\n' + status + '|\n' + error);
        }],
        success: [function (response) {
            fillTable(response);
        }]
    });
}

$(document).ready(function () {
    //применяет фильтр "за текущую дату"
    $('#currentData').change(function () {
        $('#cars_table').empty();
        if ($('#currentData').prop('checked')) {
            $('#onlyFoto').prop('checked', false);
            getCurrentDataCars();
        } else {
            getAllCars();
        }
    });
    //применяет фильтр "только с фотографией"
    $('#onlyFoto').change(function () {
        $('#cars_table').empty();
        if ($('#onlyFoto').prop('checked')) {
            $('#currentData').prop('checked', false);
            getOnlyFotoCars();
        } else {
            getAllCars();
        }
    });
    //применяет фильтр по марке авто
    $('#brandFilter').change(function () {
        var brandId = $('#brandFilter').val();
        $('#cars_table').empty();
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/hibernate/cars',
            data: 'brandId=' + brandId,
            dataType: 'json',
            error: [function(xhr, status, error) {
                alert(xhr.responseText + '|\n' + status + '|\n' + error);
            }],
            success:  [function (response) {
                fillTable(response);
            }]
        });
    });

    //заполняет таблицу объявлений при загрузке страницы
    getAllCars();

    //выполняет запрос для заполнения марок машин в фильтре
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/hibernate/brandFilter',
        dataType: 'json',
        error: [function(xhr, status, error) {
            alert(xhr.responseText + '|\n' + status + '|\n' + error);
        }],
        success: [function (response) {
            $.each(response, function () {
                $('#brandFilter').append('<option value="' + this.id + '">' + this.name + '</option>');
            });
        }]
    });
});