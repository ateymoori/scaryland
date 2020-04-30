package magazine.scary.presentation.ui.drawer

import magazine.scary.presentation.ui.drawer.MenuItemTypeEnum

data class MenuModel(
    val title: String,
    val icon: Int,
    val type: MenuItemTypeEnum
)