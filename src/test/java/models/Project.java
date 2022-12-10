package models;

public class  Project {
    private String name;
    private String announcement;
    private int type;
    private boolean showAnnouncement;

    public static class Builder {
        private final Project project;

        public Builder() {
            project = new Project();
        }

        public Builder withName(String value) {
            project.name = value;
            return this;
        }

        public Builder withAnnouncement(String value) {
            project.announcement = value;
            return this;
        }

        public Builder withType(int value) {
            project.type = value;
            return this;
        }

        public Builder withShowAnon(boolean value) {
            project.showAnnouncement = value;
            return this;
        }

        public Project build() {
            return project;
        }
    }

    public String getName() {
        return name;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public int getType() {
        return type;
    }

    public boolean isShowAnnouncement() {
        return showAnnouncement;
    }
}
