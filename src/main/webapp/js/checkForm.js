$(document).ready(function() {

    $( window ).scroll(function() {
        var scrolTOP = $('body').scrollTop();

        if (scrolTOP > 100) {
            $('.navbar').fadeOut();
            $('.btn-scroll').fadeIn();
        } else {
            $('.navbar').fadeIn();
            $('.btn-scroll').fadeOut();
        }

        $(".btn-scroll").click(function() {
            $('body').scrollTop(0)
        });
    });

    $('#form_signup').validate({
            rules: {
                nick_su: {
                    required: true,
                    minlength: 3,
                    maxlength: 16
                },
                email_su: {
                    required: true,
                    email: true
                },
                pass_su1: {
                    required: true,
                    minlength: 5,
                    maxlength: 16
                },
                pass_su2: {
                    required: true,
                    equalTo: "#passSU1"
                }
            }
        }
    );

    $('#form_signin').validate({
            rules: {
                userEmail: {
                    required: true,
                    email: true
                },
                userPassword: {
                    required: true,
                    minlength: 5,
                    maxlength: 16
                }
            },
            messages: {
                userEmail:{
                    required: "",
                    email: ""
                },
                userPassword: {
                    required: "",
                    minlength: "",
                    maxlength: ""
                }
            }
    })

    $('#formPost').validate({
        rules: {
            textPost: {
                required: true,
                maxlength: 150
            },
            refPost: {
                required: true
            }
        },
        messages: {
            textPost:{
                required: "Add discription",
                maxlength: "Only 150 char max"
            },
            refPost: {
                required: "Add the link"
            }
        }
    })
});


