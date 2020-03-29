function check(elem) {
    var but = $(elem);
    var id = but.attr("id");
    var inputField = $(`[input-id="${id}"]`);
    var val = inputField.val();
    if (val) {
        $.post("/examples/" + id, {
            value: val
        }, function(data) {
            if (data) {
                but.text("Правильно!");
                but.attr('class', 'btn btn-success');
                but.attr('disabled', true);
                inputField.attr('disabled', true);
                $.get("/examples/results", function(data) {
                    if (data) {
                        $('#scoreModal').modal();
                    }
                });
            } else {
                $('#wrongModal').modal();
                but.text("Неверно!");
                but.attr('class', 'btn btn-danger');
                var type = $('#ex-mode').val() == 'true';
                inputField.attr('disabled', !type);
                but.attr('disabled', !type);
            }
        });
    }
}
