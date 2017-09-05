package seedu.addressbook.data.person;

import seedu.addressbook.commands.AddCommand;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */

public class Address {

    public static final String EXAMPLE = "123, some street, some unit, 123456";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Invalid address entered";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public final String value;
    public Block block;
    public Unit unit;
    public Street street;
    public PostalCode postalCode;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            System.err.println(AddCommand.MESSAGE_USAGE);
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        parseAddress(trimmedAddress);
        this.value = trimmedAddress;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX) && test.split( " ").length == 4;
    }

    private void parseAddress (String address){
      String[] splitArr = address.split(" ");
      try {
          block = new Block(splitArr[0]);
          street = new Street(splitArr[1]);
          unit = new Unit(splitArr[2]);
          postalCode = new PostalCode(splitArr[3]);
      } catch (Exception e){
          System.err.println("Invalid address provided");
      }
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
