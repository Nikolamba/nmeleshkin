function validateData() {
    var result = true;
    if (!(/^[0-9]*[0-9]+$/.test($('#id').val()))) {
        alert($('#id').attr('title'));
        result = false;
    }
    if ($('#name').val() === '' || $('#name').val().length < 3) {
        alert($('#name').attr('title'));
        result = false;
    }

    if ($('#login').val() === '' || $('#login').val().length < 3) {
        alert($('#login').attr('title'));
        result = false;
    }
    if ($('#password').val() === '' || $('#password').val().length < 4) {
        alert($('#password').attr('title'));
        result = false;
    }

    if ((!(/^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$/.test($('#email').val())))) {
        alert($('#email').attr('title'));
        result = false;
    }
    if ($('#country').val() === 'error') {
        alert($('#country').attr('title'));
        result = false;
    }
    return result;
}

    $(document).ready(function () {
        $('#country').change(function () {
            var country = $('#country').val();
            $.ajax({
                type: 'get',
                url: '${pageContext.servletContext.contextPath}/city',
                data: 'country='+country,
                dataType: 'html',
                error: [function(xhr, status, error) {
                    alert(xhr.responseText + '|\n' + status + '|\n' +error);
                }],
                success: [function (response) {
                    $('#city').html(response);
                }]
            })
        });
    });