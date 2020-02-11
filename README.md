[![Build Status](https://travis-ci.org/kaitoy/pcap4j.svg?branch=v1)](https://travis-ci.org/kaitoy/pcap4j)
[![CircleCI](https://circleci.com/gh/kaitoy/pcap4j/tree/v1.svg?style=svg)](https://circleci.com/gh/kaitoy/pcap4j/tree/v1)
[![Build status](https://ci.appveyor.com/api/projects/status/github/kaitoy/pcap4j?branch=v1&svg=true)](https://ci.appveyor.com/project/kaitoy/pcap4j/branch/v1)
[![Coverage Status](https://coveralls.io/repos/kaitoy/pcap4j/badge.svg)](https://coveralls.io/r/kaitoy/pcap4j)
[![Code Quality: Java](https://img.shields.io/lgtm/grade/java/g/kaitoy/pcap4j.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/kaitoy/pcap4j/context:java)
[![Total Alerts](https://img.shields.io/lgtm/alerts/g/kaitoy/pcap4j.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/kaitoy/pcap4j/alerts)


[Russian readme](/README_ru.md)

RP
======
A Web service for capturing packages from 1C: Enterprise Configuration Repository Server with using the [Pcap4J library](http://www.pcap4j.org/) 

Contents
--------

* [Features](#features)
* [How to use](#how-to-use)
    * [System requirements](#system-requirements)
        * [Dependencies](#dependencies)
        * [Platforms](#platforms)
* [How to build](#how-to-build)
* [Contributing Code](#contributing-code)
* [License](#license)
* [Contacts](#contacts)

Features
--------

* Capturing packets via a network interface.
* Storage packets in DB
* You can get/update a packet via the API.
* All in one .jar - ***SOON***

How to use
----------

#### System requirements ####

##### Dependencies #####

* WinPcap 4.1.2

##### Platforms #####

I tested RP on the following OSes with x64 processors.

* Windows 7, 10, 2008 R2, and 2012

##### Others #####

RP needs administrator/root privileges.

How to build
------------

1. Install libpcap, WinPcap, or Npcap:

    Install WinPcap (if Windows).
    It's needed for the unit tests which are run during a build.

2. Install JDK:

    Download and install JDK 9, 10, or 11, and set the environment variable ***JAVA_HOME*** properly.

3. Start RP 

    Download and start RP.jar. - ***SOON***

Contributing Code
-----------------

1. Fork this repository.
2. Create a branch from master branch.
3. Write code.
4. Send a PR from the branch.

License
-------

[LICENSE](/LICENSE)

Contacts
--------

Ruslan Tanas (skyuser13@gmail.com)
