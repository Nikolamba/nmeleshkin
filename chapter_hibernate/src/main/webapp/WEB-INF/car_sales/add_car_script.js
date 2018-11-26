$(document).ready(function () {

    $('#brands').change(function () {
        var brand = $('#brands').val();
        $.ajax({
            type: 'get',
            url: 'http://localhost:8080/hibernate/getdata',
            data: 'brand=' + brand,
            dataType: 'json',
            error: [function (xhr, status, error) {
                alert(xhr.responseText + '|\n' + status + '|\n' + error);
            }],
            success: [function (response) {
                $('#models').attr('disabled', false).empty();
                $.each(response, function () {
                    $('#models').append('<option value = ' + this.id + '>' + this.name + '</option>');
                })
            }]
        })
    });

    $('#models').change(function () {
        if ($('#models').val() === undefined) {
            $('#models').after('wrong input');
            disabledButton();
        }
    });

    $('#year').change(function () {
        if (!parseInt($('#year').val())) {
            $('#year').after('wrong input');
            disabledButton();
        }
    });

    $('#color').change(function () {
        if (!(/^[A-z]+$/).test($('#color').val())) {
            $('#color').after('wrong input');
            disabledButton();
        }
    });

    function checkModel() {
        return $('#models').val() !== undefined
    }

    function checkYear() {
        return parseInt($('#year').val());
    }

    function checkColor() {
        return (/^[A-z]+$/).test($('#color').val());
    }

    function checkAllFields() {
        if (checkModel() && checkYear() && checkColor()) {
            $('#input_button').prop('disabled', false);
        }
    }

    setInterval(checkAllFields, 2000);
});

function disabledButton() {
    $('#input_button').prop('disabled', true);
}

function getSoldButton(status) {
    alert(status);
    if (status === true) {
        return 'Sold'
    } else return 'Not sold';
}

