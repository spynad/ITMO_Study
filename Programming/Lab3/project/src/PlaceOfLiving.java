public enum PlaceOfLiving {
    ANTARCTICA {
        @Override
        public String toString() {
            return "Antarctica";
        }
    },
    NON_ANTARCTICA {
        @Override
        public String toString() {
            return "non-Antarctica";
        }
    },
    UNKNOWN {
        @Override
        public String toString() {
            return "unknown";
        }
    }
}
