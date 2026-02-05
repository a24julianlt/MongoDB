// use domotica

// 1. 
db.mediciones.insertMany([
    {
        "sensorId": "s1",
        "ts": ISODate("2026-01-22T10:30:30Z"),
        "tipo": "temp",
        "valor": 20,
        "ubicacion": "Madrid"
    },
    {
        "sensorId": "s2",
        "ts": ISODate("2026-01-22T08:00:00Z"),
        "tipo": "hum",
        "valor": 5,
        "ubicacion": "Catoira"
    },
    {
        "sensorId": "s3",
        "ts": ISODate("2026-01-20T10:00:00Z"),
        "tipo": "temp",
        "valor": 30,
        "ubicacion": "Santiago"
    }
])

// 2. 
db.mediciones.find({ ts: { $gt: new Date(Date.now() - 24 * 60 * 60 * 1000) } }, { sensorId: 1, _id: 0 })

// [{sensorId: '1'}, {sensorId:'2'}] depende del día de ejecución


// 3. 
db.mediciones.find({ valor: { $gt: 10 } }, { sensorId: 1, _id: 0 })

// [ { sensorId: '1' }, { sensorId: '3' } ]


// 4. 
db.mediciones.updateMany({}, { $set: { ubicacion: "Santiago" } })

/*{
  acknowledged: true,
  insertedId: null,
  matchedCount: 3,
  modifiedCount: 3,
  upsertedCount: 0
}*/


// 5. 
db.mediciones.deleteMany({ ts: { $lt: ISODate("2026-01-21T00:00:00Z") } })
// { acknowledged: true, deletedCount: 1 }

