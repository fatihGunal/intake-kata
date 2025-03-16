package be.athumi

class WineShop(var items: List<Wine>) {
    fun annualInventoryUpdate() {
        // Wine Shop logic
        for (wine in items) {
            if (wine.name != "Bourdeaux Conservato" && wine.name != "Bourgogne Conservato" && !wine.name.startsWith("Event")) {
                if (wine.price > 0) {
                    if (wine.name != "Wine brewed by Alexander the Great") {
                        wine.price = wine.price - 1
                    }
                }
            } else {
                if (wine.price < 100) {
                    wine.price = wine.price + 1

                    if (wine.name.startsWith("Event")) {
                        if (wine.expiresInYears < 8) {
                            if (wine.price < 100) {
                                wine.price = wine.price + 1
                            }
                        }

                        if (wine.expiresInYears < 3) {
                            if (wine.price < 100) {
                                wine.price = wine.price + 2
                            }
                        }
                    }
                }
            }

            if (wine.name != "Wine brewed by Alexander the Great") {
                wine.expiresInYears = wine.expiresInYears - 1
            } else if (wine.price < 0) {
                wine.price = 0
            }

            if (wine.expiresInYears < 0) {
                if (!wine.name.contains("Conservato")) {
                    if (!wine.name.contains("Event")) {
                        if (wine.price > 0) {
                            if (wine.name != "Wine brewed by Alexander the Great") {
                                wine.price = wine.price - 1
                            }
                        }
                    } else {
                        wine.price = wine.price - wine.price
                    }
                } else {
                    if (wine.price < 100) {
                        wine.price = wine.price + 1
                    }
                }
            }

            if (wine.price < 0) {
                wine.price = 0
            }
        }
    }
}