package be.athumi

class WineShop(var items: List<Wine>) {
    fun annualInventoryUpdate() {
        // Wine Shop logic
        for (i in items.indices) {
            if (items[i].name != "Bourdeaux Conservato" && items[i].name != "Bourgogne Conservato" && !items[i].name.startsWith("Event")) {
                if (items[i].price > 0) {
                    if (items[i].name != "Wine brewed by Alexander the Great") {
                        items[i].price = items[i].price - 1
                    }
                }
            } else {
                if (items[i].price < 100) {
                    items[i].price = items[i].price + 1

                    if (items[i].name.startsWith("Event")) {
                        if (items[i].expiresInYears < 8) {
                            if (items[i].price < 100) {
                                items[i].price = items[i].price + 1
                            }
                        }

                        if (items[i].expiresInYears < 3) {
                            if (items[i].price < 100) {
                                items[i].price = items[i].price + 2
                            }
                        }
                    }
                }
            }

            if (items[i].name != "Wine brewed by Alexander the Great") {
                items[i].expiresInYears = items[i].expiresInYears - 1
            } else if (items[i].price < 0) {
                items[i].price = 0
            }

            if (items[i].expiresInYears < 0) {
                if (!items[i].name.contains("Conservato")) {
                    if (!items[i].name.contains("Event")) {
                        if (items[i].price > 0) {
                            if (items[i].name != "Wine brewed by Alexander the Great") {
                                items[i].price = items[i].price - 1
                            }
                        }
                    } else {
                        items[i].price = items[i].price - items[i].price
                    }
                } else {
                    if (items[i].price < 100) {
                        items[i].price = items[i].price + 1
                    }
                }
            }

            if (items[i].price < 0) {
                items[i].price = 0
            }
        }
    }
}