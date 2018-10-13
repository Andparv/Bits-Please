// JavaScript source code
$(window).load(function () {
    $('a[data-gravatar-hash]').prepend(function (index) {
        var hash = $(this).attr('data-gravatar-hash')
        return '<img width="100" height="100" alt="" src="http://www.gravatar.com/avatar.php?size=100&gravatar_id=' + hash + '">'
    })
})