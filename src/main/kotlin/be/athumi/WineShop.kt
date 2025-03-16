package be.athumi

private const val BASE_PRICE_INCREASE = 1
private const val INCREASE_FACTOR = 2
private const val BASE_PRICE_DECREASE = 1
private const val DECREASE_FACTOR = 2

class WineShop(var items: List<Wine>) {
    fun annualInventoryUpdate() {
        for (wine in items) {
            when (wine.getWineCategory()) {
                WineCategory.AGING -> increaseWinePrice(wine, false)
                WineCategory.EVENT -> increaseWinePrice(wine, true)
                WineCategory.LEGENDARY -> adjustPriceForLegendaryWine(wine)
                WineCategory.ECO_BREWED -> decreaseEcoBrewedWine(wine)
                WineCategory.STANDARD -> decreaseWinePrice(wine)
            }

            if (wine.getWineCategory() != WineCategory.LEGENDARY) {
                decreaseExpiresInYears(wine)
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
        wine.price -= BASE_PRICE_DECREASE
    }

    private fun applyPriceDecreaseWithFactor(wine: Wine) {
        wine.price -= BASE_PRICE_DECREASE * DECREASE_FACTOR
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

    private fun shouldIncreasePrice(wine: Wine): Boolean {
        return wine.price < 100
    }

    private fun applyEventWinePriceIncrease(wine: Wine) {
        wine.price += getEventWinePriceIncrease(wine)
    }

    private fun unexpiredWine(wine: Wine): Boolean {
        return wine.expiresInYears > 0
    }

    private fun getEventWinePriceIncrease(wine: Wine): Int {
        return if (wine.expiresInYears < 3) 4 else 2
    }

    private fun applyPriceIncrease(wine: Wine) {
        wine.price += BASE_PRICE_INCREASE
    }

    private fun applyPriceIncreaseWithFactor(wine: Wine, isEventWine: Boolean) {
        if (isEventWine) {
            wine.price = 0
        } else {
            wine.price += BASE_PRICE_INCREASE * INCREASE_FACTOR
        }
    }

    private fun isEventWine(wine: Wine, isEventWine: Boolean): Boolean {
        return isEventWine && wine.expiresInYears < 8
    }

    private fun decreaseExpiresInYears(wine: Wine) {
        wine.expiresInYears -= 1
    }

    enum class WineCategory {
        AGING, EVENT, LEGENDARY, ECO_BREWED, STANDARD
    }

    fun Wine.getWineCategory(): WineCategory {
        return when {
            this.name.contains("Conservato") -> WineCategory.AGING
            this.name.startsWith("Event") -> WineCategory.EVENT
            this.name == "Wine brewed by Alexander the Great" -> WineCategory.LEGENDARY
            this.name.startsWith("Eco") -> WineCategory.ECO_BREWED
            else -> WineCategory.STANDARD
        }
    }

    private fun adjustPriceForLegendaryWine(wine: Wine) {
        // Legendary wine does not change its price, so we do nothing here.
    }

    private fun decreaseEcoBrewedWine(wine: Wine) {
        // I'm not sure if I should continue developing this, since the explanation clearly states
        // that no code should be added, only refactored.
    }
}