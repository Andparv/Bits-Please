// JavaScript source code
$('.search-link').on('click', function () {
    $(this).hide()
    $('.search-bar').show()
    setTimeout(closeSearch, 5000)
});
function closeSearch() {
    
    $('.search-bar').hide()
    $('.search-link').show()
}
