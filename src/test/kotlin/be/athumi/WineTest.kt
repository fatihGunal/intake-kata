package be.athumi

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class WineTest {
    @Test
    fun `tasting or testing wine`() {
        assertThat(Wine("name", 0, 0)).isNotNull
    }

    @Test
    fun `a shop without wine is no shop, is it`() {
        val shop = WineShop(listOf(Wine("name", 0, 0)))

        assertThat(shop).isNotNull
    }

    @Test
    fun `should decrease in price when annual inventory audit with standard wine`() {
        val shop = WineShop(listOf(Wine("Standard Wine", 50, 10)))

        shop.annualInventoryUpdate()

        val testWine = shop.items.first()

        val expiresInYears = testWine.expiresInYears
        val price = testWine.price
        val name = testWine.name

        assertThat(shop).isNotNull
        assertThat(expiresInYears).isEqualTo(10 - 1)
        assertThat(price).isEqualTo(50 - 1)
        assertThat(name).isEqualTo("Standard Wine")
    }

    @Test
    fun `should decrease twice in price when annual inventory audit with expired standard wine`() {
        val shop = WineShop(listOf(Wine("Standard Wine", 50, 0)))

        shop.annualInventoryUpdate()

        assertThat(shop).isNotNull
        assertThat(shop.items.first().price).isEqualTo(50 - 2)
    }

    @Test
    fun `should never decrease in price when standard wine is expired`() {
        val shop = WineShop(listOf(Wine("Standard Wine", -1, -1)))

        shop.annualInventoryUpdate()

        assertThat(shop).isNotNull
        assertThat(shop.items.first().price).isEqualTo(0)
    }

    @Test
    fun `should increase in price when wine is cellar or aging wine`() {
        val shop = WineShop(listOf(Wine("Bourdeaux Conservato", 50, 10)))

        shop.annualInventoryUpdate()

        assertThat(shop).isNotNull
        assertThat(shop.items.first().price).isEqualTo(50 + 1)
    }

    @Test
    fun `should increase twice in price when wine is cellar or aging wine and is expired`() {
        val shop = WineShop(listOf(Wine("Bourdeaux Conservato", 50, -1)))

        shop.annualInventoryUpdate()

        assertThat(shop).isNotNull
        assertThat(shop.items.first().price).isEqualTo(50 + 2)
    }

    @Test
    fun `should never exceeds 100 euro`() {
        val shop = WineShop(listOf(Wine("Bourdeaux Conservato", 100, 10)))

        shop.annualInventoryUpdate()

        assertThat(shop).isNotNull
        assertThat(shop.items.first().price).isEqualTo(100)
    }

    @Test
    fun `should never change in price and expiration years when wine is brewed by Alexander the Great`() {
        val shop = WineShop(listOf(Wine("Wine brewed by Alexander the Great", 50, 10)))

        shop.annualInventoryUpdate()

        val testWine = shop.items.first()

        val expiresInYears = testWine.expiresInYears
        val price = testWine.price

        assertThat(shop).isNotNull
        assertThat(expiresInYears).isEqualTo(10)
        assertThat(price).isEqualTo(50)
    }

    @Test
    fun `should increase in price when wine is a event wine`() {
        val shop = WineShop(listOf(Wine("Event Wine", 50, 10)))

        shop.annualInventoryUpdate()

        assertThat(shop).isNotNull
        assertThat(shop.items.first().price).isEqualTo(50 + 1)
    }

    @Test
    fun `should drop to zero in price when wine is event wine and is expired`() {
        val shop = WineShop(listOf(Wine("Event Wine", 50, -1)))

        shop.annualInventoryUpdate()

        assertThat(shop).isNotNull
        assertThat(shop.items.first().price).isEqualTo(0)
    }

    @Test
    fun `should increase in price with 2 when wine is event wine and only 7 years or less before expiration`() {
        val shop = WineShop(listOf(Wine("Event Wine", 50, 7)))

        shop.annualInventoryUpdate()

        assertThat(shop).isNotNull
        assertThat(shop.items.first().price).isEqualTo(50 + 2)
    }

    @Test
    fun `should increase in price with 4 when wine is event wine and only 2 years or less before expiration`() {
        val shop = WineShop(listOf(Wine("Event Wine", 50, 2)))

        shop.annualInventoryUpdate()

        assertThat(shop).isNotNull
        assertThat(shop.items.first().price).isEqualTo(50 + 4)
    }
}