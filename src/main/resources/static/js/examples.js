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
                but.text(correctMsg);
                but.attr('class', 'btn btn-success');
                but.attr('disabled', true);
                inputField.attr('disabled', true);
                $.get("/examples/results", function(data) {
                    console.log(data.countFailed);
                    console.log(data.countTotal);
                    console.log(data.done);
                    if (data.done) {
                        $('#scoreModal').modal();
                    }
                });
            } else {
                $('#wrongModal').modal();
                but.text(incorrectMsg);
                but.attr('class', 'btn btn-danger');
                var type = $('#ex-mode').val() == 'true';
                inputField.attr('disabled', !type);
                but.attr('disabled', !type);
            }
        });
    }
}

function getResult() {
    $.get("/examples/results", function(data) {
        console.log(data.countFailed);
        console.log(data.countTotal);
        console.log(data.done);
        $('#failed').html(data.countFailed);
        $('#resolved').html(data.countResolved);
        $('#total').html(data.countTotal);
        $('#done').html(data.done ? msgYes : msgNo);
        $('#resultModal').modal();
    });
}
