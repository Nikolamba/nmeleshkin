function ajaxFunction() {
    var checkStatus;
    if ($('#check').is(':checked')) checkStatus = 'all';
    else checkStatus = 'done';
    $.ajax({
        type: 'get',
        url: 'http://localhost:8080/hibernate/items',
        data: 'check=' + checkStatus,
        dataType: 'json',
        error: [function(xhr, status, error) {
            alert(xhr.responseText + '|\n' + status + '|\n' + error);
        }],
        success: [function (response) {
            $('#items_table tbody tr').remove();
            $.each(response, function () {
                $('#items_table tbody').append('<tr><td>'+ this.id +'</td>' +
                    '<td>' + this.desc + '</td>' +
                    '<td>' + this.created + '</td>' +
                    '<td>' + this.done + '</td></tr>');
            });
        }]
    });
}

function sendItem() {
    var desc = $('#desc').val();
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/hibernate/items',
        data: 'desc=' + desc,
        error: [function(xhr, status, error) {
            alert(xhr.responseText + '|\n' + status + '|\n' + error);
        }],
        success: [function (response) {
            ajaxFunction();
        }]
    });
}

$(document).ready(function () {
    ajaxFunction();
    setInterval(ajaxFunction, 5000);

    $('#check').change(function () {
        ajaxFunction();
    });
});

