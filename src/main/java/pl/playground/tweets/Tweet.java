package pl.playground.tweets;

final class Tweet {

    private final String id;
    private final String message;
    private final String authorId;

    Tweet(String id, String message, String authorId) {
        this.id = id;
        this.message = message;
        this.authorId = authorId;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthorId() {
        return authorId;
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                ", authorId='" + authorId + '\'' +
                '}';
    }
}
