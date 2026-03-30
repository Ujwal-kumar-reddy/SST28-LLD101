class BoardFactory {
    
    public static BoardEntity createEntity(String type, int start, int end) {
        if (type.equals("snake")) return new Snake(start, end);
        else return new Ladder(start, end);
    }
}