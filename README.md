# intersoft-hal-forms-util
hal forms utils based by otto.edison.hal

When using hypermedia based REST resources you will need a common standard. Besides Spring HATEOAS otto.edison created a library that helps to create that format.
intersoft created even more details follwing the hal forms standard ( https://rwcbook.github.io/hal-forms/ ) and helps you creating forms.

## Use

  halRepresentation = new HalFormsRepresentation(
      linkingTo()
          .self(selfUriString)
          .build(),
      withTemplates(
          defaultTemplate("Formular", POST, erstelleProperties()))
  );
  
  private List<Property> erstelleProperties() {
   // See usages of Propertybuilder.
    return asList(
        propertyBuilder("Feld A")
            .withPrompt("Feld A")
            .with("type", PROPERTY_TYPE_STRING)
            .withRequired(true)
            .withReadOnly(true)
            .build());
}
