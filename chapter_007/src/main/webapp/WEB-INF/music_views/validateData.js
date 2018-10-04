function validate() {
    var result = true;
    if (isNaN($('#id').val())) {
        alert($('#id').attr('title'));
        result = false;
    }
    if ((/^[A-zА-яЁё]+$/).test($('#name'))) {
        alert($('#name').attr('title'));
        result = false;
    }
    if ((/^\d{6}$/).test($('#zipcode'))) {
        alert($('#zipcode').attr('title'));
        result = false;
    }
    if ((/^[A-zА-яЁё]+$/).test($('#city'))) {
        alert($('#city').attr('title'));
        result = false;
    }
    if ((/^[A-zА-яЁё]+$/).test($('#street'))) {
        alert($('#street').attr('title'));
        result = false;
    }
    if (isNaN($('#housenum').val())) {
        alert($('#housenum').attr('title'));
        result = false;
    }
    return result;
}

$(document).ready(function () {
    $('#addres_filter').change(function () {
        $('#adresses').prop('disabled', false);
        $('#roles').prop('disabled', true);
        $('#music_types').prop('disabled', true);
    });
    $('#role_filter').change(function () {
        $('#adresses').prop('disabled', true);
        $('#roles').prop('disabled', false);
        $('#music_types').prop('disabled', true);
    });
    $('#music_type_filter').change(function () {
        $('#adresses').prop('disabled', true);
        $('#roles').prop('disabled', true);
        $('#music_types').prop('disabled', false);
    });

    $('#adresses').change(function () {
        var adress = $('#adresses').val();
        if (adress === 'error') {
            alert($('#adresses').attr('title'));
            return false;
        }
        $.ajax({
            type: 'get',
            url: '${pageContext.servletContext.contextPath}/ajaxData',
            data: 'adress=' + adress,
            dataType: 'json',
            error: [function(xhr, status, error) {
                alert(xhr.responseText + '|\n' + status + '|\n' +error);
            }],
            success: [function (response) {
                html = getHtmlUser(response);
                $('#usersTable td').parent().remove();
                $('#usersTable tr:last').after(html);
            }]
        })
    });

    $('#roles').change(function () {
        var role = $('#roles').val();
        if (role === 'error') {
            alert($('#roles').attr('title'));
            return false;
        }
        $.ajax({
            type: 'get',
            url: '${pageContext.servletContext.contextPath}/ajaxData',
            data: 'role=' + role,
            dataType: 'json',
            error: [function(xhr, status, error) {
                alert(xhr.responseText + '|\n' + status + '|\n' +error);
            }],
            success: [function (response) {
                html = '';
                $.each(response, function () {
                    html += getHtmlUser(this);
                });
                $('#usersTable td').parent().remove();
                $('#usersTable tr:last').after(html);
            }]
        })
    });

    $('#music_types').change(function () {
        var musicType = $('#music_types').val();
        if (musicType === 'error') {
            alert($('#music_types').attr('title'));
            return false;
        }
        $.ajax({
            type: 'get',
            url: '${pageContext.servletContext.contextPath}/ajaxData',
            data: 'musicType=' + musicType,
            dataType: 'json',
            error: [function(xhr, status, error) {
                alert(xhr.responseText + '|\n' + status + '|\n' +error);
            }],
            success: [function (response) {
                html = '';
                $.each(response, function () {
                    html += getHtmlUser(this);
                });
                $('#usersTable td').parent().remove();
                $('#usersTable tr:last').after(html);
            }]
        })
    });

});
function getHtmlUser(fullUser) {
    var user = fullUser.user;
    var adress = fullUser.adress;
    var role = fullUser.role;
    var musicTypeList = fullUser.musicTypeList;
    var html = '<tr><td>' + user.id + '</td><td>' + user.name + '</td>';
    html += '<td>' + adress.zipCode + ', ' + adress.city + ', ' + adress.street + ', ' + adress.houseNum + '</td>';
    html += '<td>' + role.role + '</td><td>';
    $.each(musicTypeList, function () {
        html += this.type + ', ';
    });
    html += '</td></tr>';
    return html;
}
