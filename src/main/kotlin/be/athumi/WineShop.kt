package be.athumi

private const val BASE_PRICE_INCREASE = 1
private const val INCREASE_FACTOR = 2
private const val BASE_PRICE_DECREASE = 1
private const val DECREASE_FACTOR = 2

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
                when {
                    isAgingWine -> increaseWinePrice(wine, isEventWine)
                    isEventWine -> wine.price = 0
                    wine.price > 0 && !isLegendaryWine -> wine.price--
                }
            }
        }
    }

    private fun decreaseWinePrice(wine: Wine) {
        if (shouldDecreaseWinePrice(wine)) {
            if (unexpiredWine(wine)) {
                applyPriceDecrease(wine)
            } else {
                applyPriceDecreaseWithFactor(wine)
            }
        } else {
            wine.price = 0
        }
    }

    private fun shouldDecreaseWinePrice(wine: Wine): Boolean {
        return wine.price > 0
    }

    private fun applyPriceDecrease(wine: Wine) {
        wine.price -= BASE_PRICE_INCREASE
    }

    private fun applyPriceDecreaseWithFactor(wine: Wine) {
        wine.price -= DECREASE_FACTOR * DECREASE_FACTOR
    }

    private fun increaseWinePrice(wine: Wine, isEventWine: Boolean) {
        if (shouldIncreasePrice(wine)) {
            if (unexpiredWine(wine)) {
                if (isEventWine(wine, isEventWine)) {
                    applyEventWinePriceIncrease(wine)
                } else {
                    applyPriceIncrease(wine)
                }
            } else {
                applyPriceIncreaseWithFactor(wine, isEventWine)
            }
        }
    }

    // --- Tip: Uncle Bob's clean code suggests creating the following methods to improve the readability of the function above.
    // --- However, I don't always agree with this approach, as it can sometimes lead to an excessive number of functions.
    private fun shouldIncreasePrice(wine: Wine): Boolean {
        return wine.price < 100
    }

    private fun applyEventWinePriceIncrease(wine: Wine) {
        val priceIncrease: Int
        if (wine.expiresInYears < 0) {
            priceIncrease = 0
        } else {
            priceIncrease = getEventWinePriceIncrease(wine)
        }
        wine.price += priceIncrease
    }

    private fun unexpiredWine(wine: Wine): Boolean {
        return wine.expiresInYears < 0
    }

    private fun getEventWinePriceIncrease(wine: Wine): Int {
        return if (wine.expiresInYears < 3) 4 else 2
    }

    private fun applyPriceIncrease(wine: Wine) {
        wine.price += BASE_PRICE_INCREASE
    }

    private fun applyPriceIncreaseWithFactor(wine: Wine, isEventWine: Boolean) {
        if (isEventWine) {
            wine.expiresInYears = 0
        } else {
            wine.price += BASE_PRICE_INCREASE * INCREASE_FACTOR
        }
    }

    private fun isEventWine(wine: Wine, isEventWine: Boolean): Boolean {
        return isEventWine && wine.expiresInYears < 7
    }
}