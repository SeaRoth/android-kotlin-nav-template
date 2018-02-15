package com.searoth.template.other

/**
 * Created by SeaRoth_2 on 2/14/2018.
 */

//"id": 3,
//"name": "Torta a la Plancha",
//"imageUrl": "http://www.gamermessenger.com/savorlyapp/food/wendy.png",
//"rating": 4.3,
//"price": 8.99,
//"description": "• Pollo Pierna y Lomo• Pavo Ahumado• Carne Asada• Jamón con Queso• Veggie ",
//"notes": "Con Aguacate $1.00 Más",
//"ingredients": [
//"2 tablespoons fresh thyme leaves, roughly chopped",
//"2 pounds thinly cut flap meat",
//"Salt and freshly ground black pepper",
//"1 tablespoon crumbled dried Mexican oregano",
//"1 cup freshly squeezed orange juice (from 3 oranges)",
//"2 tablespoons vegetable oil",
//"4 bolillo rolls, halved lengthwise",
//"Mayonnaise"
//],
//"business": {
//    "name": "Wendy's Tortas",
//    "link": "http://wendystortas.com/",
//    "imageUrl": "http://www.gamermessenger.com/savorlyapp/business/wendy.PNG",
//    "address": "1418 W Beverly Blvd",
//    "hours": "",
//    "rating": 4
//}
class Models{

    class User(val id: Int, val name: String, val email: String, val imageUrl: String, val username: String)

    class LocalFeed(var user: User, val meals: List<FoodItem>)

    class FoodItem(val id: Int, val name: String, val imageUrl: String,
                   val rating: Float, val price: Double, var description: String,
                   var notes: String, val ingredients: Array<String>,val business: Business)

    class Business(val name: String, val link: String, val imageUrl: String, val address: String, val hours: String, val rating: Float)
}