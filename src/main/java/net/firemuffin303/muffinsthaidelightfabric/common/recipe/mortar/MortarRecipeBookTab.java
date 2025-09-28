package net.firemuffin303.muffinsthaidelightfabric.common.recipe.mortar;

public enum MortarRecipeBookTab {
    MEALS("meals"),
    MISC("misc");

    public final String name;

    MortarRecipeBookTab(String name){this.name = name;}

    public static MortarRecipeBookTab findByName(String name) {
        for (MortarRecipeBookTab value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
