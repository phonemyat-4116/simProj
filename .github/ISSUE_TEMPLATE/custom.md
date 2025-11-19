---
name: Custom issue template
about: Describe this issue template's purpose here.
title: ''
labels: ''
assignees: ''

---

name: Bug Report
description: Report a problem in the system
title: "[Bug]: "
labels: ["bug", "triage"]
body:
  - type: textarea
    id: description
    attributes:
      label: Bug Description
      description: What happened?
    validations:
      required: true
  - type: textarea
    id: steps
    attributes:
      label: Steps to Reproduce
      description: Provide exact steps
      placeholder: "1. Go to...\n2. Click...\n3. See error"
  - type: input
    id: expected
    attributes:
      label: Expected Result
    validations:
      required: true
  - type: input
    id: actual
    attributes:
      label: Actual Result
    validations:
      required: true
  - type: dropdown
    id: severity
    attributes:
      label: Severity Level
      options:
        - Minor
        - Major
        - Critical
