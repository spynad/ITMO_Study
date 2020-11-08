public enum Direction {
    HORIZONTAL {
        @Override
        public String toString() {
            return "horizontal";
        }
    },
    VERTICAL {
        @Override
        public String toString() {
            return "vertical";
        }
    },

    UNKNOWN {
        @Override
        public String toString() {
            return "UNKNOWN";
        }
    }

}
