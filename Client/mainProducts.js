
let guardar = document.getElementById("guardar");
let modificar = document.getElementById("modificar");

guardar.style.display = "block"
modificar.style.display = "none"

const getProducts = () => {
    $.ajax({
        type: 'GET',
        headers: { "Accept": "application/json" },
        url: 'http://localhost:8084/Actividad2_war_exploded/products'
    }).done(res => {
        let listProducts = res;
        let table = $("#table");

        table.append(
        "<tr class='bg-info text-dark'>"+
        +"<th scope='col'>Product Code</th>"
        +"<th scope='col'>Product Name</th>"
        +"<th scope='col'>Product Line</th>"
        +"<th scope='col'>Product Scale</th>"
        +"<th scope='col'>Product Vendor</th>"
        +"<th scope='col'>Product Description</th>"
        +"<th scope='col'>Quantity In Stock</th>"
        +"<th scope='col'>Buy Price</th>"
        +"<th scope='col'>MSRP</th>"
        +"</tr>")

        for(let i = 0; i < listProducts.length; i++){
            table.append("<tr>"
            +"<td>"+res[i].productCode + "</td>"
            +"<td>"+res[i].productName + "</td>"
            +"<td>"+res[i].productLine+"</td> "
            +"<td>"+res[i].productScale + "</td>"
            +"<td>"+res[i].productVendor+ "</td>"
            +"<td>"+res[i].productDescription+"</td>"
            +"<td>"+res[i].quantityInStock+"</td>"
            +"<td>"+res[i].buyPrice + "</td>"
            +"<td>"+res[i].msrp + "</td>"
            +"<td><button class='btn btn-warning' onclick='findById("+res[i].productCode+")'>Modificar</button></td>"
            +"<td><button class='btn btn-danger' onclick='delete("+res[i].productCode+")'>Borrar</button></td>"
            +"</tr>")
        }

    });
};

getProducts();

const findById = id => {
    $.ajax({
        type: 'GET',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/products'+id
    }).done(res => {
        console.log(res)
        guardar.style.display = "none"
        modificar.style.display = "block"
        
        document.getElementById("productCode").value=res.productCode
        document.getElementById("productName").value=res.productName
        document.getElementById("productLine").value=res.productLine
        document.getElementById("productScale").value=res.productScale
        document.getElementById("productVendor").value=res.productVendor
        document.getElementById("productDescription").value=res.productDescription
        document.getElementById("quantityInStock").value=res.quantityInStock
        document.getElementById("buyPrice").value=res.buyPrice
        document.getElementById("msrp").value=res.msrp
       
        
    });
}


const save = () => {
    let products = new Object();
    products.productCode = document.getElementById("productCode").value
    products.productName = document.getElementById("productName").value
    products.productLine = document.getElementById("productLine").value
    products.productScale = document.getElementById("productScale").value
    products.productVendor = document.getElementById("productVendor").value
    products.productDescription = document.getElementById("productDescription").value
    products.quantityInStock = document.getElementById("quantityInStock").value
    products.buyPrice = document.getElementById("buyPrice").value
    products.msrp = document.getElementById("msrp").value

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/products/save',
        data: products
    }).done(res => {
        console.log(res);
        guardar.style.display = "block"
        modificar.style.display = "none"
    });
}

/*const delete =() id => {
    $.ajax({
        type: 'POST',
        headers: { 
            "Accept": "application/json",
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/products/delete/'+id
    }).done(res => {
        console.log(res);
    });
}*/


const update = () => {

    let products = new Object();
    let id = document.getElementById("productCode").value
    products.productName = document.getElementById("productName").value
    products.productLine = document.getElementById("productLine").value
    products.productScale = document.getElementById("productScale").value
    products.productVendor = document.getElementById("productVendor").value
    products.productDescription = document.getElementById("productDescription").value
    products.quantityInStock = document.getElementById("quantityInStock").value
    products.buyPrice = document.getElementById("buyPrice").value
    products.msrp = document.getElementById("msrp").value
    

    $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: 'http://localhost:8084/Actividad2_war_exploded/products/save'+id,
        data: products
    }).done(res => {
        console.log(res)
        guardar.style.display = "block"
        modificar.style.display = "none"

        document.getElementById("productCode").value=""
        document.getElementById("productName").value=""
        document.getElementById("productLine").value=""
        document.getElementById("productScale").value=""
        document.getElementById("productVendor").value=""
        document.getElementById("productDescription").value=""
        document.getElementById("quantityInStock").value=""
        document.getElementById("buyPrice").value=""
        document.getElementById("msrp").value=""
    
        
    });
}

