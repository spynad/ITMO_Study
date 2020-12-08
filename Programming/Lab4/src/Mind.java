public class Mind {
    private boolean hurt;

    public AncientStar transformFantastically(AncientStar star){
        String modernity, org, hurt;
        if (star.isModernity()) {
            modernity = "Modern";
        } else {
            modernity = "Ancient";
        }

        if (star.isTropical()) {
            org = "Tropical";
        } else {
            org = "Non-tropical";
        }

        if(this.hurt) {
            hurt = "Painful";
        } else {
            hurt = "Non-painful";
        }

        System.out.printf("%s %s %s star is fantastically transformed by %s mind\n", modernity,
                org, star.getOrigin().toString(), hurt);
        star.setFantasticalTransformed(true);

        return star;
    }

    public void setHurt(boolean hurt) {
        this.hurt = hurt;
    }
}
