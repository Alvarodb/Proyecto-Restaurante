function loaded() {
    categorias();
    subcategorias();
    renderCart();
    $("#sidebar").on("click", "li", function () {
        buscar($(this).text());
    });
}

$(loaded);

function list(categorias) {
    var listado = $("#categorias");
    listado.html("");
    listado.append("<li>ALL</li>");
    categorias.forEach((c) => {
        row(listado, c);
    });

}

function row(listado, categoria) {
    var list = document.createElement("li");
    list.innerHTML = categoria.nombre;
    listado.append(list);
}
function categorias() {
    $.ajax({type: "GET", url: "/Restaurante/restaurante/categorias/", contentType: "application/json"})
            .then((categorias) => {
                list(categorias);
            },
                    (error) => {
                alert(errorMessage(error.status));
            });
}
function subcategorias() {
    $.ajax({type: "GET", url: "/Restaurante/restaurante/platillos/", contentType: "application/json"})
            .then((platillos) => {
                listPlatillos(platillos);
            },
                    (error) => {
                alert(errorMessage(error.status));
            });
}
function listPlatillos(platillos) {
    var aux = 1;
    var listado = $("#accordion");
    listado.html("");
    platillos.forEach((platillo) => {
        var cont = aux.toString(10);
        $('<div>', {
            id: 'card',
            class: 'card'
        }).append($('<div>', {
            id: "heading" + cont,
            class: 'card-header',
            html: "<h5 class='mb-0'>" +
                    "<h5  class='text-dark' data-toggle='collapse' data-target='#collapse" + cont + "' aria-expanded='true' aria-controls='collapse" + cont + "'>" +
                    platillo.categoria.nombre +
                    "</h5>" +
                    "</h5>"
        }).append($('<div>', {
            id: "collapse" + cont,
            class: 'collapse show'
        }).append($('<div>', {
            class: 'card-body',
            html: "<dl class='row justify-content-around'>" +
                    "<dt class='col-sm-3'>" + platillo.nombre + "</dt>" +
                    "<dt class='col-sm-3'>" + "$" + platillo.precio + "</dt>" +
                    "<button id='add" + cont + "' type='button' data-toggle='modal' data-target='#modalOpciones' class='col-sm-2 btn btn-light btn-sm btn-cart text-white'>+</button>" +
                    "<div class='modal' id='modalOpciones' tabindex='-1' role='dialog' aria-labelledby='ModalLabel' aria-hidden='true'>" +
                    "</dl>" +
                    "<p class='text-muted'>" + platillo.descripcion + "</p>"

        })))).appendTo('#accordion');
        listado.find("#add" + cont).on("click", () => {
            settings(platillo);
        });
        aux += 1;
    });
}
function listOpciones(opciones, platillo) {
    var listado = $("#modalOpciones");
    listado.html("");
    $('<div>', {
        class: 'modal-dialog modal-dialog-centered',
        id: 'initial',
        role: 'document'
    }).append($('<div>', {
        class: 'modal-content',
        id: 'content'
    }).append($('<div>', {
        class: 'modal-header',
        html:
                "<button type='button' id='close' class='close' data-dismiss='modal' aria-label='Close'" +
                "</button>" +
                "<span aria-hidden='true'>&times;</span>"
    }))).appendTo('#modalOpciones');

    $('#content').append($('<div>', {
        class: 'modal-body',
        html: "<h4 class='modal-title' id='ModalLabelTitle'>" + platillo.nombre + "</h4>" +
                "<p class='text-muted'><br>" + platillo.descripcion + "</p>"
    }));

    opciones.forEach((opc) => {
        $('#content').append($('<div>', {
            class: 'option',
            html: "<h5 id=" + opc.nombre + " class='mb-0'>" + opc.nombre + "</h5>"
        }));
        if (opc.tipo === 'mult') {
            opc.adicionalList.forEach((adi) => {
                $('#content').append($('<div>', {
                    class: 'checkbox',
                    html: "<label><input type='checkbox' id='" + adi.nombre + "'" + " value=''>" + adi.nombre + "   $" + adi.precio + "</label>"
                }));
            });
        }
        else if (opc.tipo === 'single') {
            opc.adicionalList.forEach((adi) => {
                $('#content').append($('<div>', {
                    class: 'radio',
                    html: "<label><input type='radio' name='" + opc.nombre + "' id='" + adi.nombre + "'" + " value=''>" + adi.nombre + "   $" + adi.precio + "</label>"
                }));
            });
        }
    });

   

    $('#content').append($('<div>', {
        class: 'modal-footer',
        html: "<div class='container'>" +
                "<div class='row'>" +
                "<div class='col'>" +
                "<input type='number' id='cant' name='quantity' class='form-control text-center' value='1'>" +
                "</div>" +
                "<div class='col'>" +
                "<button type='submit' id='check' class='btn btn-primary btn-block' data-attach-loading=''>ADD</button>" +
                "</div>" +
                "</div>" +
                "</div>"
    }));
    listado.find("#check").on("click", () => {
        cartItemAdd(platillo, opciones, $('#cant').val());
    });
}
function cartItemAdd(platillo, opciones, cant) {
    var total = 0;
    var extras = 0;
    var opcionesDeseadas = [];

    $('#modalOpciones').modal('hide');
    $('#items').append(
            "<li id=" + platillo.nombre + ">" +
            "<button type='button' id='del' class='cart-btn text-muted'>-</button>" +
            "<span class='price pull-right' id='precio'></span>" +
            "<span class='name'>" +
            "<span class='quantity font-weight-bold'>" + cant + "×" + "</span>" +
            platillo.nombre +
            "</span>" +
            "</li>"
            );

    opciones.forEach((opc) => {
        $('#' + platillo.nombre).append("<h6>" + opc.nombre + "</h6>");
        if (opcionesDeseadas.length > 0) {
            opcionesDeseadas.forEach((d) => {
                if (d.nombre !== opc.nombre) {
                    opcionesDeseadas.push(JSON.parse(JSON.stringify(opc)));
                }
            });
        } else {
            opcionesDeseadas.push(JSON.parse(JSON.stringify(opc)));
        }
        opc.adicionalList.forEach((adi) => {
            if ($('#' + adi.nombre).is(":checked")) {
                extras = extras + adi.precio;
                $('#' + platillo.nombre).append(
                        "<span style='float:right;'>" + adi.nombre + "($" + adi.precio + ")</span><br><br>"

                        );
            } else {
                var cont = 0;
                opcionesDeseadas.forEach((o) => {
                    o.adicionalList.forEach((a) => {
                        if (a.nombre === adi.nombre) {
                            o.adicionalList.splice(cont, 1);
                        }
                        cont += 1;
                    });
                });

            }
        });
    });
    $("#" + platillo.nombre).find("#precio").text("$" + (platillo.precio + extras) * cant);
    total = total + ((platillo.precio + extras) * cant);
    //ir guardando elementos en la sesion
    platilloOrden = {nombre: platillo.nombre,
        opcionList: opcionesDeseadas,
        precio: (platillo.precio + extras) * cant
    };
    detalleOrden = {platillo: platilloOrden,
        cantidad: cant
    };

    cart(detalleOrden);

    $("#" + platillo.nombre).find("#del").on("click", () => {
        cartItemDel(platillo.nombre);
    });
}
function cart(detalleOrden) {
    $.ajax({type: "PUT", url: "/Restaurante/restaurante/cart", data: JSON.stringify(detalleOrden), contentType: "application/json"})
            .then(( ) => {
                //no hace nada por ahora, solo actualiza el carrito en la sesion
                totalCart();
            },
                    (error) => {
                alert(errorMessage(error.status));
            });
}
function renderCart() {
    //trae el carrito de la sesion y lo renderiza en la pagina
    $.ajax({type: "GET", url: "/Restaurante/restaurante/cart", contentType: "application/json"})
            .then((items) => {
                totalCart();
                items.forEach((item) => {
                    cartItemRender(item.platillo, item.platillo.opcionList, item.cantidad);
                });
            },
                    (error) => {
                alert(errorMessage(error.status));
            });
}
function totalCart() {
    $.ajax({type: "POST", url: "/Restaurante/restaurante/cart"})
            .then((num) => {
                total = num;
                $("#total").text("Order Total: $" + total);
            },
                    (error) => {
                alert(errorMessage(error.status));
            });
}
function cartItemRender(platillo, opciones, cant) {
    $('#items').append(
            "<li id=" + platillo.nombre + ">" +
            "<button type='button' id='del' class='cart-btn text-muted'>-</button>" +
            "<span class='price pull-right'>$" + platillo.precio + "</span>" +
            "<span class='name'>" +
            "<span class='quantity font-weight-bold'>" + cant + "×" + "</span>" +
            platillo.nombre +
            "</span>" +
            "</li>"
            );
    $("#" + platillo.nombre).find("#del").on("click", () => {
        cartItemDel(platillo.nombre);
    });


    opciones.forEach((opc) => {
        $('#' + platillo.nombre).append("<h6>" + opc.nombre + "</h6>");
        opc.adicionalList.forEach((adi) => {
            $('#' + platillo.nombre).append(
                    "<span style='float:right;'>" + adi.nombre + "($" + adi.precio + ")</span><br><br>"
                    );
        });
    });
}
function cartItemDel(nombre) {
    platillo = {nombre: nombre};
    $.ajax({type: "DELETE", url: "/Restaurante/restaurante/cart/" + platillo.nombre, contentType: "application/json"})
            .then((items) => {
                totalCart();
                if (items.length > 0) {
                    $('#items').html("");
                    items.forEach((item) => {
                        cartItemRender(item.platillo, item.platillo.opcionList, item.cantidad);
                    });
                } else {
                    $('#items').html("");
                }
            },
                    (error) => {
                alert(errorMessage(error.status));
            });
}
function settings(platillo) {
    $.ajax({type: "POST", url: "/Restaurante/restaurante/opciones?nombre=" + platillo.nombre, contentType: "application/json"})
            .then((opciones) => {
                listOpciones(opciones, platillo);
            },
                    (error) => {
                alert(errorMessage(error.status));
            });
}
function buscar(categoria) {
    categoria = {nombre: categoria};
    if (categoria.nombre === "ALL") {
        subcategorias();
        return;
    }
    $.ajax({type: "POST", url: "/Restaurante/restaurante/platillos?nombre=" + categoria.nombre, contentType: "application/json"})
            .then((platillos) => {
                listPlatillos(platillos);
            },
                    (error) => {
                alert(errorMessage(error.status));
            });
}
function errorMessage(status) {
    switch (status) {
        case 404:
            return "Registro no encontrado";
        case 403:
        case 405:
            return "Usuario no autorizado";
        case 406:
            return "Registro duplicado";
        default:
            return "Error: " + status;
    }
}
