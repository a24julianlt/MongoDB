// use tienda

// 1. 
db.clientes.insertMany([
    {
        "idCliente": "c1",
        "nombre": "cliente1",
        "emails": [
            "c1-Email1@gmail.com",
            "c1-Email2@gmail.com"
        ],
        "direcciones": [
            "c1 Direccion1",
            "c1 Direccion2"
        ]
    },
    {
        "idCliente": "c2",
        "nombre": "cliente2",
        "emails": [
            "c2-Email1@gmail.com",
            "c2-Email2@gmail.com"
        ],
        "direcciones": [
            "c2 Direccion1",
            "c2 Direccion2"
        ]
    }
])

db.pedidos.insertMany([
    {
        "idPedido": "p1",
        "idCliente": "c1",
        "fecha": ISODate("2026-01-22T10:30:30Z"),
        "lineas": [
            {
                "sku": "sku1",
                "nombre": "patinete",
                "cantidad": 1,
                "precio": 200.50
            }
        ],
        "estado": "Enviado"
    },
    {
        "idPedido": "p2",
        "idCliente": "c1",
        "fecha": ISODate("2026-01-20T12:00:00Z"),
        "lineas": [
            {
                "sku": "sku2",
                "nombre": "juego de mesa",
                "cantidad": 2,
                "precio": 21
            }
        ],
        "estado": "En espera"
    },
    {
        "idPedido": "p3",
        "idCliente": "c2",
        "fecha": new Date(),
        "lineas": [
            {
                "sku": "sku3",
                "nombre": "mando",
                "cantidad": 3,
                "precio": 99.99
            }
        ],
        "estado": "Enviado"
    }
])


// 2. 
db.pedidos.find({ idCliente: "c1" }, { idPedido: 1, idCliente: 1, _id: 0 })

/*
[
  { idPedido: 'p1', idCliente: 'c1' },
  { idPedido: 'p2', idCliente: 'c1' }
]*/


// 3. Como no hay suficiente cantidad añadimos más al pedido1

db.pedidos.updateMany({ idPedido: "p1" }, {
    $set: {
        "lineas": [
            {
                "sku": "sku1",
                "nombre": "patinete",
                "cantidad": 1,
                "precio": 200.50
            },
            {
                "sku": "skuAñadido",
                "nombre": "linea Añadida",
                "cantidad": 100,
                "precio": 50
            }]
    }
})

// 3 formas de hacerlo
db.pedidos.aggregate([
    { $unwind: "$lineas" },
    {
        $group: {
            _id: "$idPedido",
            idCliente: { $first: "$idCliente" },
            totalCantidad: { $sum: "$lineas.cantidad" }
        }
    },
    { $match: { totalCantidad: { $gt: 100 } } }
])

db.pedidos.find({ $expr: { $gt: [{ $sum: "$lineas.cantidad" }, 100] } }, { idPedido: 1, idCliente: 1, _id: 0 })

// cantidad total mayor que 100
db.pedidos.aggregate([
    {
        $project: {
            _id: 0,
            idPedido: 1,
            idCliente: 1,
            fecha: 1,
            estado: 1,
            cantidadTotal: { $sum: "$lineas.cantidad" }
        }
    },
    {
        $match: {
            cantidadTotal: { $gt: 100 }
        }
    }
])

// lineas que cantidad sea mayor a 100
db.pedidos.aggregate([
    { $unwind: "$lineas" },
    {
        $match: {
            "lineas.cantidad": { $gt: 99 } // le bajamos a 99 porque la cantidad es 100
        }
    },
    {
        $project: {
            _id: 0,
            idPedido: 1,
            idCliente: 1,
            fecha: 1,
            estado: 1,
            lineas: "$lineas"
        }
    },
])

// 4. 



// 5.

db.pedidos.updateOne({ "idCliente": "c2" }, { $set: { "emails.0": "correoNuevo@gmail.com" } })

