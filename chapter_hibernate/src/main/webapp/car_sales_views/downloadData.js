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

$(document).ready(function () {
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/hibernate/cars',
        dataType: 'json',
        error: [function(xhr, status, error) {
            alert(xhr.responseText + '|\n' + status + '|\n' + error);
        }],
        success: [function (response) {
            $.each(response, function () {
                $('#cars_table').append('<tr><td><img src="' + this.picturePath + '"></td>'
                    + '<td>Brand: ' + this.model.brand.name
                    + '<br>Model: ' + this.model.name
                    + '<br>Color: ' + this.color
                    + '<br>Body type: ' + this.bodyType.name
                    + '<br>Year of issue: ' + this.year
                    + '<br>Seller: ' + this.seller.name
                    + '<br>Status: ' + this.status
                    + '</td></tr>');
            });
        }]
    });
});