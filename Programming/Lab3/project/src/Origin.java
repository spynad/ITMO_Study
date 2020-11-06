public enum Origin {
    MARINE {
        @Override
        public String toString() {
            return "marine";
        }
    },
    TERRESTRIAL {
        @Override
        public String toString() {
            return "terrestrial";
        }
    },
    UNKNOWN {
        @Override
        public String toString() {
            return "unknown";
        }
    }
}
