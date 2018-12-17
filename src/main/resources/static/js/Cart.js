if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready)
} else {
    ready()
}

function ready() {
    console.log("Store");


    var addToCartButtons = document.getElementsByClassName('getstarted-button')
    for (var i = 0; i < addToCartButtons.length; i++) {
        console.log("Sain elemendi");
        var button = addToCartButtons[i];
        button.addEventListener('click', addToCartClicked);

    }
    document.getElementsByClassName('btn-purchase')[0].addEventListener('click', purchaseClicked)

}




function addToCartClicked(event) {
    console.log("clicked")
    var button = event.target
    var shopItem = button.parentElement.parentElement
    
    var title = shopItem.getElementsByClassName('phoneTitle')[0].innerText
    console.log((shopItem.getElementsByClassName('price')[0].innerText.indexOf("Price: ")))
    if (shopItem.getElementsByClassName('price')[0].innerText.indexOf("Price: ") > -1) {
        var pricetext = shopItem.getElementsByClassName('price')[0].innerText.replace('Price: ', '')
        pricetext.replace('€', '')
        var price = parseFloat(pricetext)
        var keel = "Inglise"
    }
    else {
        var pricetext = shopItem.getElementsByClassName('price')[0].innerText.replace('Hind: ', '')
        pricetext.replace('€', '')
        var price = parseFloat(pricetext)
        var keel = "Eesti"
    }
    addItemToCart(title, price, keel)


    console.log(title, price)
}
function removeCartItem(event) {
    var buttonClicked = event.target
    buttonClicked.parentElement.parentElement.remove()
    updateCartTotal()
}
function quantityChanged(event) {
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 1
    }
    updateCartTotal()
}
function addItemToCart(title, price, keel) {
    var cartRow = document.createElement('div')
    cartRow.classList.add('cart-row')
    var cartItems = document.getElementsByClassName('cart-items')[0]
    var cartItemNames = cartItems.getElementsByClassName('cart-item-title')
    for (var i = 0; i < cartItemNames.length; i++) {
        if (cartItemNames[i].innerText == title) {
            alert('This item is already added to the cart')
            return
        }
    }
    if(keel == "Inglise"){var cartRowContents = `
                                  <div class="cart-item cart-column">

                                      <span class="cart-item-title">${title}</span>
                                  </div>
                                  <span class="cart-price cart-column">${price}</span>
                                  <div class="cart-quantity cart-column">
                                      <input class="cart-quantity-input" type="number" value="1">
                                      <button class="btn btn-danger" type="button">REMOVE</button>
                                  </div>`}
                                  else {
                                  var cartRowContents = `
                                          <div class="cart-item cart-column">

                                              <span class="cart-item-title">${title}</span>
                                          </div>
                                          <span class="cart-price cart-column">${price}</span>
                                          <div class="cart-quantity cart-column">
                                              <input class="cart-quantity-input" type="number" value="1">
                                              <button class="btn btn-danger" type="button">EEMALDA</button>
                                          </div>`}

    cartRow.innerHTML = cartRowContents
    cartItems.append(cartRow)
    updateCartTotal()
    cartRow.getElementsByClassName('btn-danger')[0].addEventListener('click', removeCartItem)
    cartRow.getElementsByClassName('cart-quantity-input')[0].addEventListener('change', quantityChanged)
}
function updateCartTotal() {
    var cartItemContainer = document.getElementsByClassName('cart-items')[0]
    var cartRows = cartItemContainer.getElementsByClassName('cart-row')
    var total = 0
    for (var i = 0; i < cartRows.length; i++) {
        var cartRow = cartRows[i]
        var priceElement = cartRow.getElementsByClassName('cart-price')[0]
        var quantityElement = cartRow.getElementsByClassName('cart-quantity-input')[0]
        var price = parseFloat(priceElement.innerText.replace('$', ''))
        var quantity = quantityElement.value
        total = total + (price * quantity)
    }
    total = Math.round(total * 100) / 100
    document.getElementsByClassName('cart-total-price')[0].innerText = total + '€'
}

function purchaseClicked() {
    alert('Thank you for your purchase')
    var cartItems = document.getElementsByClassName('cart-items')[0]
    while (cartItems.hasChildNodes()) {
        cartItems.removeChild(cartItems.firstChild)
    }
    updateCartTotal()
}


