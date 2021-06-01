package studygroup;

import java.time.LocalDateTime;

/**
 * Студеньческая группа и информация по ней.
 */
public class StudyGroup implements Comparable<StudyGroup>{

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long studentsCount; //Значение поля должно быть больше 0
    private long expelledStudents; //Значение поля должно быть больше 0
    private Integer transferredStudents; //Значение поля должно быть больше 0, Поле не может быть null
    private FormOfEducation formOfEducation = null; //Поле может быть null
    private Person groupAdmin = null; //Поле может быть null

    public StudyGroup(int id, String name, Coordinates coordinates, long studentsCount, long expelledStudents, Integer transferredStudents, FormOfEducation formOfEducation, Person groupAdmin) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.studentsCount = studentsCount;
        this.expelledStudents = expelledStudents;
        this.transferredStudents = transferredStudents;
        this.formOfEducation = formOfEducation;
        this.groupAdmin = groupAdmin;
    }


    /**
     * @return Возвращает Id группы.
     */
    public int getId() {
        return id;
    }

    /**
     * @return Возвращает имя группы.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Возвращает координаты группы.
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

    /**
     * @return Возвращает дату создания группы группы.
     */
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @return Возвращает колличество студентов группы.
     */
    public long getStudentsCount() {
        return studentsCount;
    }

    /**
     * @return Возвращает колличество исключенных студентов группы.
     */
    public long getExpelledStudents() {
        return expelledStudents;
    }

    /**
     * @return Возвращает колличество переведённых студентов группы.
     */
    public Integer getTransferredStudents() {
        return transferredStudents;
    }

    /**
     * @return Возвращает формат обучения группы.
     */
    public FormOfEducation getFormOfEducation() {
        return formOfEducation;
    }

    /**
     * @return Возвращает администратора группы.
     */
    public Person getGroupAdmin() {
        return groupAdmin;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(StudyGroup studyGroup) {
        if (studentsCount == studyGroup.getStudentsCount()) {
            if (id < studyGroup.getId()) {
                return -1;
            } else {
                return 1;
            }
        } else {
            if (studentsCount < studyGroup.getStudentsCount()) {
                return -1;
            } else {
                return 1;
            }

        }
    }


}