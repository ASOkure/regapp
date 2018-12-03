package uk.ac.nesc.idsd.exception;

public enum ErrorCodes {
    NO_REGISTRY_ID(1001, "No registry ID was given!"),
    NO_RECORD_FOUND(1002, "The record you are querying does not exist!"),
    RECORD_LOOKUP_ERROR(1003, "Error when viewing registerId. "),

    USER_LOOKUP_ERROR(2001, "Could not load your user profile for security checks. Please logout and login again to retry this action."),
    USER_SESSION_EXPIRED(2002, "User session expired. "),
    NO_USER_FOUND(2003, "User does not exist. "),
    WRONG_USER_OR_CREDENTIAL(2004, "User name and password are wrong!"),
    SAVING_NULL_USER(2005, "User instance you are saving is null."),
    SAVING_USER_WITH_EMAIL_EXCEPTION(2006, "The email address you are changing to has already been registered in the registry. Please pick another one or login with account registered with this email address. If you think this is an error. Please contact the I-DSD Project Manager, Jillian Bryce at jillian.bryce@glasgow.ac.uk"),
    EMPTY_USERNAME_GIVEN(2007, "Please specify the username."),
    USER_CENTRE_ROLE_EXCEPTION(2008, "We are experiencing issues to get your centre and roles. Please log out and login again to retry"),
    RETRIEVE_PORTAL_USER_ERROR(2009, "You are trying to retrieve clinical or research users, but found patient user"),

    CANNOT_ASSIGN_PATIENT_ACCESS_AS_NONE_OWNER(3001, "You are not allowed to assigned participant access to this record as a non-owner"),
    NO_PATIENT_EMAIL_GIVEN(3002, "Please give patient email address to provide participant access for this record."),
    NO_REPEAT_PATIENT_EMAIL_GIVEN(3003, "Please give patient email address again to provide participant access for this record."),
    PATIENT_EMAIL_NOT_MATCH(3004, "The 2 email addresses given do not match"),
    PATIENT_USER_CREATION_FAILURE(3005, "Failed to create patient user"),
    DUPLICATED_PATIENT_EMAIL_FOR_RECORD(3006, "This email has already been provided access to a participant record"),
    PATIENT_ACCOUNT_NOT_EXIST(3007, "Patient account does not exist"),
    PATIENT_USER_RETRIEVAL_ERROR(3008, "Exception when retrieving patient user account"),
    PATIENT_ACCOUNT_DOES_NOT_LINK_TO_RECORD(3009, "Patient user account is not linked with any record"),
    NO_PATIENT_USERNAME_GIVEN(3010, "Patient username is not given."),
    PATIENT_SESSION_EXPIRED(3011, "Patient user is empty in session. "),
    NO_PATIENT_ACCESS_ROLE_IS_GIVEN(3012, "You are not assigned with patient access role, and cannot access the records."),
    NO_RECORD(9001, "No record can be found.");

    private final int id;
    private final String message;

    ErrorCodes(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return id + " : " + message;
    }
}