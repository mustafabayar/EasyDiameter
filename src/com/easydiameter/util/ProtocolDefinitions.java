package com.easydiameter.util;

public interface ProtocolDefinitions {

	/* Diameter Message Header Flags */
	public static final byte	HEADER_FLAG_NONE									= (byte) 0x00;
	public static final byte	HEADER_FLAG_E										= (byte) 0x20;
	public static final byte	HEADER_FLAG_P										= (byte) 0x40;
	public static final byte	HEADER_FLAG_R										= (byte) 0x80;
	public static final byte	HEADER_FLAG_P_E										= (byte) 0x60;
	public static final byte	HEADER_FLAG_R_T										= (byte) 0x90;
	public static final byte	HEADER_FLAG_R_P										= (byte) 0xC0;
	public static final byte	HEADER_FLAG_R_P_T									= (byte) 0xD0;

	/* Diameter Message Header Flag Bits Masks */
	public static final int		HEADER_MASK_BIT_R									= 0x80;
	public static final int		HEADER_MASK_BIT_P									= 0x40;
	public static final int		HEADER_MASK_BIT_E									= 0x20;
	public static final int		HEADER_MASK_BIT_T									= 0x10;
	public static final int		HEADER_MASK_RESERVED								= 0x0F;

	/* AVP Flags */
	public static final byte	AVP_FLAG_V											= (byte) 0x80;
	public static final byte	AVP_FLAG_M											= (byte) 0x40;
	public static final byte	AVP_FLAG_V_M										= (byte) 0xC0;
	public static final byte	AVP_FLAG_NONE										= (byte) 0x00;

	/* Diameter AVP Flag Bits Masks */
	public static final int		AVP_MASK_BIT_V										= 0x80;
	public static final int		AVP_MASK_BIT_M										= 0x40;
	public static final int		AVP_MASK_BIT_P										= 0x20;
	public static final int		AVP_MASK_RESERVED									= 0x1F;

	/* Diameter Message Header Length Definitions */
	public static final int		DIAMETER_MSG_HDR_LEN								= 20;

	/* Diameter AVP Header Length Definitions */
	public static final int		AVP_HDR_LEN_WITH_VENDOR								= 12;
	public static final int		AVP_HDR_LEN_WITHOUT_VENDOR							= 8;

	/* Available Diameter Versions */
	public static final byte	DIAMETER_VERSION									= 1;

	/* InetAddress Types */
	public static final int		ADDRESS_TYPE_IPv4									= 1;
	public static final int		ADDRESS_TYPE_IPv6									= 2;

	/* AVP Data Types - DT stands for "Data Type" */
	public static final int		DT_UNKNOWN											= -1;
	public static final int		DT_OCTET_STRING										= 0;
	public static final int		DT_INTEGER_32										= 1;
	public static final int		DT_INTEGER_64										= 2;
	public static final int		DT_UNSIGNED_32										= 3;
	public static final int		DT_UNSIGNED_64										= 4;
	public static final int		DT_FLOAT_32											= 5;
	public static final int		DT_FLOAT_64											= 6;
	public static final int		DT_GROUPED											= 7;
	public static final int		DT_ADDRESS											= 8;
	public static final int		DT_TIME												= 9;
	public static final int		DT_UTF8STRING										= 10;
	public static final int		DT_DIAMETER_IDENTITY								= 11;
	public static final int		DT_DIAMETER_URI										= 12;
	public static final int		DT_ENUMERATED										= 13;
	public static final int		DT_IP_FILTER_RULE									= 14;
	public static final int		DT_QoS_FILTER_RULE									= 15;

	/* Command Codes */
	public static final int		COMMAND_CER_CEA										= 257;					/* [RFC6733] */
	public static final int		COMMAND_RAR_RAA										= 258;					/* [RFC6733] */
	public static final int		COMMAND_AMR_AMA										= 260;					/* [RFC4004] */
	public static final int		COMMAND_HAR_HAA										= 262;					/* [RFC4004] */
	public static final int		COMMAND_AAR_AAA										= 265;					/* [RFC7155] */
	public static final int		COMMAND_DER_DEA										= 268;					/* [RFC4072] */
	public static final int		COMMAND_ACR_ACA										= 271;					/* [RFC6733] */
	public static final int		COMMAND_CCR_CCA										= 272;					/* [RFC4006] */
	public static final int		COMMAND_ASR_ASA										= 274;					/* [RFC6733] */
	public static final int		COMMAND_STR_STA										= 275;					/* [RFC6733] */
	public static final int		COMMAND_DWR_DWA										= 280;					/* [RFC6733] */
	public static final int		COMMAND_DPR_DPA										= 282;					/* [RFC6733] */
	public static final int		COMMAND_UAR_UAA										= 283;					/* [RFC4740] */
	public static final int		COMMAND_SAR_SAA										= 284;					/* [RFC4740] */
	public static final int		COMMAND_LIR_LIA										= 285;					/* [RFC4740] */
	public static final int		COMMAND_MAR_MAA										= 286;					/* [RFC4740] */
	public static final int		COMMAND_RTR_RTA										= 287;					/* [RFC4740] */
	public static final int		COMMAND_PPR_PPA										= 288;					/* [RFC4740] */
	public static final int		COMMAND_PDR_PDA										= 314;					/* [RFC5224] */

	/* AVP Codes - AC stands for "AVP Code" */
	public static final int		AC_PROXY_STATE										= 33;					/* [RFC6733] */
	public static final int		AC_HOST_IP_ADDRESS									= 257;					/* [RFC6733] */
	public static final int		AC_AUTH_APPLICATION_ID								= 258;					/* [RFC6733] */
	public static final int		AC_ACCT_APPLICATION_ID								= 259;					/* [RFC6733] */
	public static final int		AC_VENDOR_SPECIFIC_APPLICATION_ID					= 260;					/* [RFC6733] */
	public static final int		AC_REDIRECT_HOST_USAGE								= 261;					/* [RFC6733] */
	public static final int		AC_REDIRECT_MAX_CACHE_TIME							= 262;					/* [RFC6733] */
	public static final int		AC_SESSION_ID										= 263;					/* [RFC6733] */
	public static final int		AC_ORIGIN_HOST										= 264;					/* [RFC6733] */
	public static final int		AC_SUPPORTED_VENDOR_ID								= 265;					/* [RFC6733] */
	public static final int		AC_VENDOR_ID										= 266;					/* [RFC6733] */
	public static final int		AC_FIRMWARE_REVISION								= 267;					/* [RFC6733] */
	public static final int		AC_RESULT_CODE										= 268;					/* [RFC6733] */
	public static final int		AC_PRODUCT_NAME										= 269;					/* [RFC6733] */
	public static final int		AC_SESSION_BINDING									= 270;					/* [RFC6733] */
	public static final int		AC_SESSION_SERVER_FAILOVER							= 271;					/* [RFC6733] */
	public static final int		AC_MULTI_ROUND_TIME_OUT								= 272;					/* [RFC6733] */
	public static final int		AC_DISCONNECT_CAUSE									= 273;					/* [RFC6733] */
	public static final int		AC_AUTH_REQUEST_TYPE								= 274;					/* [RFC6733] */
	public static final int		AC_AUTH_GRACE_PERIOD								= 276;					/* [RFC6733] */
	public static final int		AC_AUTH_SESSION_STATE								= 277;					/* [RFC6733] */
	public static final int		AC_ORIGIN_STATE_ID									= 278;					/* [RFC6733] */
	public static final int		AC_FAILED_AVP										= 270;					/* [RFC6733] */
	public static final int		AC_PROXY_HOST										= 280;					/* [RFC6733] */
	public static final int		AC_ERROR_MESSAGE									= 281;					/* [RFC6733] */
	public static final int		AC_ROUTE_RECORD										= 282;					/* [RFC6733] */
	public static final int		AC_DESTINATION_REALM								= 283;					/* [RFC6733] */
	public static final int		AC_PROXY_INFO										= 284;					/* [RFC6733] */
	public static final int		AC_RE_AUTH_REQUEST_TYPE								= 285;					/* [RFC6733] */
	public static final int		AC_ACCOUNTING_SUB_SESSION_ID						= 287;					/* [RFC6733] */
	public static final int		AC_AUTHORIZATION_LIFETIME							= 291;					/* [RFC6733] */
	public static final int		AC_REDIRECT_HOST									= 292;					/* [RFC6733] */
	public static final int		AC_DESTINATION_HOST									= 293;					/* [RFC6733] */
	public static final int		AC_ERROR_REPORTING_HOST								= 294;					/* [RFC6733] */
	public static final int		AC_TERMINATION_CAUSE								= 295;					/* [RFC6733] */
	public static final int		AC_ORIGIN_REALM										= 296;					/* [RFC6733] */
	public static final int		AC_EXPERIMENTAL_RESULT								= 297;					/* [RFC6733] */
	public static final int		AC_EXPERIMENTAL_RESULT_CODE							= 298;					/* [RFC6733] */
	public static final int		AC_INBAND_SECURITY_ID								= 299;					/* [RFC6733] */
	public static final int		AC_E2E_SEQUENCE										= 300;					/* [RFC6733] */
	public static final int		AC_DRMP												= 301;					/* [RFC7944] */
	public static final int		AC_MIP_FA_TO_HA_SPI									= 318;					/* [RFC4004] */
	public static final int		AC_MIP_FA_TO_MN_SPI									= 319;					/* [RFC4004] */
	public static final int		AC_MIP_REG_REQUEST									= 320;					/* [RFC4004] */
	public static final int		AC_MIP_REG_REPLY									= 321;					/* [RFC4004] */
	public static final int		AC_MIP_MN_AAA_AUTH									= 322;					/* [RFC4004] */
	public static final int		AC_MIP_HA_TO_FA_SPI									= 323;					/* [RFC4004] */
	public static final int		AC_MIP_MN_TO_FA_MSA									= 325;					/* [RFC4004] */
	public static final int		AC_MIP_FA_TO_MN_MSA									= 326;					/* [RFC4004] */
	public static final int		AC_MIP_FA_TO_HA_MSA									= 328;					/* [RFC4004] */
	public static final int		AC_MIP_HA_TO_FA_MSA									= 329;					/* [RFC4004] */
	public static final int		AC_MIP_MN_TO_HA_MSA									= 331;					/* [RFC4004] */
	public static final int		AC_MIP_HA_TO_MN_MSA									= 332;					/* [RFC4004] */
	public static final int		AC_MIP_MOBILE_NODE_ADRESS							= 333;					/* [RFC4004] */
	public static final int		AC_MIP_HOME_AGENT_ADDRESS							= 334;					/* [RFC4004] */
	public static final int		AC_MIP_NONCE										= 335;					/* [RFC4004] */
	public static final int		AC_MIP_CANDIDATE_HOME_AGENT_HOST					= 336;					/* [RFC4004] */
	public static final int		AC_MIP_FEATURE_VECTOR								= 337;					/* [RFC4004] */
	public static final int		AC_MIP_AUTH_INPUT_DATA_LENGTH						= 338;					/* [RFC4004] */
	public static final int		AC_MIP_AUTHENTICATOR_LENGTH							= 339;					/* [RFC4004] */
	public static final int		AC_MIP_AUTHENTICATOR_OFFSET							= 340;					/* [RFC4004] */
	public static final int		AC_MIP_MN_AAA_SPI									= 341;					/* [RFC4004] */
	public static final int		AC_MIP_FILTER_RULE									= 342;					/* [RFC4004] */
	public static final int		AC_MIP_SESSION_KEY									= 343;					/* [RFC4004] */
	public static final int		AC_MIP_FA_CHALLENGE									= 344;					/* [RFC4004] */
	public static final int		AC_MIP_ALGORITHM_TYPE								= 345;					/* [RFC4004] */
	public static final int		AC_MIP_REPLAY_MODE									= 346;					/* [RFC4004] */
	public static final int		AC_MIP_ORIGINATING_FOREIGN_AAA						= 347;					/* [RFC4004] */
	public static final int		AC_MIP_HOME_AGENT_HOST								= 348;					/* [RFC4004] */
	public static final int		AC_ACCOUNTING_INPUT_OCTETS							= 363;					/* [RFC7155andRFC4004] */
	public static final int		AC_ACCOUNTING_OUTPUT_OCTETS							= 364;					/* [RFC7155andRFC4004] */
	public static final int		AC_ACCOUNTING_INPUT_PACKETS							= 365;					/* [RFC7155andRFC4004] */
	public static final int		AC_ACCOUNTING_OUTPUT_PACKETS						= 366;					/* [RFC7155andRFC4004] */
	public static final int		AC_MIP_MSA_LIFETIME									= 367;					/* [RFC4004] */
	public static final int		AC_SIP_ACCOUNTING_INFORMATION						= 368;					/* [RFC4740] */
	public static final int		AC_SIP_ACCOUNTING_SERVER_URI						= 369;					/* [RFC4740] */
	public static final int		AC_SIP_CREDIT_CONTROL_SERVER_URI					= 370;					/* [RFC4740] */
	public static final int		AC_SIP_SERVER_URI									= 371;					/* [RFC4740] */
	public static final int		AC_SIP_SERVER_CAPABILITIES							= 372;					/* [RFC4740] */
	public static final int		AC_SIP_MANDATORY_CAPABILITY							= 373;					/* [RFC4740] */
	public static final int		AC_SIP_OPTIONAL_CAPABILITY							= 374;					/* [RFC4740] */
	public static final int		AC_SIP_SERVER_ASSIGNMENT_TYPE						= 375;					/* [RFC4740] */
	public static final int		AC_SIP_AUTH_DATA_ITEM								= 376;					/* [RFC4740] */
	public static final int		AC_SIP_AUTHENTICATION_SCHEME						= 377;					/* [RFC4740] */
	public static final int		AC_SIP_ITEM_NUMBER									= 378;					/* [RFC4740] */
	public static final int		AC_SIP_AUTHENTICATE									= 379;					/* [RFC4740] */
	public static final int		AC_SIP_AUTHORIZATION								= 380;					/* [RFC4740] */
	public static final int		AC_SIP_AUTHENTICATION_INFO							= 381;					/* [RFC4740] */
	public static final int		AC_SIP_NUMBER_AUTH_ITEMS							= 382;					/* [RFC4740] */
	public static final int		AC_SIP_DEREGISTRATION_REASON						= 383;					/* [RFC4740] */
	public static final int		AC_SIP_REASON_CODE									= 384;					/* [RFC4740] */
	public static final int		AC_SIP_REASON_INFO									= 385;					/* [RFC4740] */
	public static final int		AC_SIP_VISITED_NETWORK_ID							= 386;					/* [RFC4740] */
	public static final int		AC_SIP_USER_AUTHORIZATION_TYPE						= 387;					/* [RFC4740] */
	public static final int		AC_SIP_SUPPORTED_USER_DATA_TYPE						= 388;					/* [RFC4740] */
	public static final int		AC_SIP_USER_DATA									= 389;					/* [RFC4740] */
	public static final int		AC_SIP_USER_DATA_TYPE								= 390;					/* [RFC4740] */
	public static final int		AC_SIP_USER_DATA_CONTENTS							= 391;					/* [RFC4740] */
	public static final int		AC_SIP_USER_DATA_ALREADY_AVAILABLE					= 392;					/* [RFC4740] */
	public static final int		AC_SIP_METHOD										= 393;					/* [RFC4740] */
	public static final int		AC_NAS_FILTER_RULE									= 400;					/* [RFC7155] */
	public static final int		AC_TUNNELING										= 401;					/* [RFC7155] */
	public static final int		AC_CHAP_AUTH										= 402;					/* [RFC7155] */
	public static final int		AC_CHAP_ALGORTIHM									= 403;					/* [RFC7155] */
	public static final int		AC_CHAP_IDENT										= 404;					/* [RFC7155] */
	public static final int		AC_CHAP_RESPONSE									= 405;					/* [RFC7155] */
	public static final int		AC_ACCOUNTING_AUTH_METHOD							= 406;					/* [RFC7155] */
	public static final int		AC_QoS_FILTER_RULE									= 407;					/* [RFC7155] */
	public static final int		AC_ORIGIN_AAA_PROTOCOL								= 408;					/* [RFC7155] */
	public static final int		AC_CC_CORRELATION_ID								= 411;					/* [RFC4006] */
	public static final int		AC_CC_INPUT_OCTETS									= 412;					/* [RFC4006] */
	public static final int		AC_CC_MONEY											= 413;					/* [RFC4006] */
	public static final int		AC_CC_OUTPUT_OCTETS									= 414;					/* [RFC4006] */
	public static final int		AC_CC_REQUEST_NUMBER								= 415;					/* [RFC4006] */
	public static final int		AC_CC_REQUEST_TYPE									= 416;					/* [RFC4006] */
	public static final int		AC_CC_SERVICE_SPECIFIC_UNITS						= 417;					/* [RFC4006] */
	public static final int		AC_CC_SESSION_FAILOVER								= 418;					/* [RFC4006] */
	public static final int		AC_CC_SUB_SESSION_ID								= 419;					/* [RFC4006] */
	public static final int		AC_CC_TIME											= 420;					/* [RFC4006] */
	public static final int		AC_CC_TOTAL_OCTETS									= 421;					/* [RFC4006] */
	public static final int		AC_CHECK_BALANCE_RESULT								= 422;					/* [RFC4006] */
	public static final int		AC_COST_INFORMATION									= 423;					/* [RFC4006] */
	public static final int		AC_COST_UNIT										= 424;					/* [RFC4006] */
	public static final int		AC_CURRENCY_CODE									= 425;					/* [RFC4006] */
	public static final int		AC_CREDIT_CONTROL									= 426;					/* [RFC4006] */
	public static final int		AC_CREDIT_CONTROL_FAILURE_HANDLING					= 427;					/* [RFC4006] */
	public static final int		AC_DIRECT_DEBITING_FAILURE_HANDLING					= 428;					/* [RFC4006] */
	public static final int		AC_EXPONENT											= 429;					/* [RFC4006] */
	public static final int		AC_FINAL_UNIT_INDICATION							= 430;					/* [RFC4006] */
	public static final int		AC_GRANTED_SERVICE_UNIT								= 431;					/* [RFC4006] */
	public static final int		AC_RATING_GROUP										= 432;					/* [RFC4006] */
	public static final int		AC_REDIRECT_ADDRESS_TYPE							= 433;					/* [RFC4006] */
	public static final int		AC_REDIRECT_SERVER									= 434;					/* [RFC4006] */
	public static final int		AC_REDIRECT_SERVER_ADDRESS							= 435;					/* [RFC4006] */
	public static final int		AC_REQUEST_ACTION									= 436;					/* [RFC4006] */
	public static final int		AC_REQUEST_SERVICE_UNIT								= 437;					/* [RFC4006] */
	public static final int		AC_RESTRICTION_FILTER_RULE							= 438;					/* [RFC4006] */
	public static final int		AC_SERVICE_IDENTIFIER								= 439;					/* [RFC4006] */
	public static final int		AC_SERVICE_PARAMETER_INFO							= 440;					/* [RFC4006] */
	public static final int		AC_SERVICE_PARAMETER_TYPE							= 441;					/* [RFC4006] */
	public static final int		AC_SERVICE_PARAMETER_VALUE							= 442;					/* [RFC4006] */
	public static final int		AC_SUBSCRIPTION_ID									= 443;					/* [RFC4006] */
	public static final int		AC_SUBSCRIPTION_ID_DATA								= 444;					/* [RFC4006] */
	public static final int		AC_UNIT_VALUE										= 445;					/* [RFC4006] */
	public static final int		AC_USED_SERVICE_UNIT								= 446;					/* [RFC4006] */
	public static final int		AC_VALUE_DIGITS										= 447;					/* [RFC4006] */
	public static final int		AC_VALIDITY_TIME									= 448;					/* [RFC4006] */
	public static final int		AC_FINAL_UNIT_ACTION								= 449;					/* [RFC4006] */
	public static final int		AC_SUBSCRIPTION_ID_TYPE								= 450;					/* [RFC4006] */
	public static final int		AC_TARIFF_TIME_CHANGE								= 451;					/* [RFC4006] */
	public static final int		AC_TARIFF_CHANGE_USAGE								= 452;					/* [RFC4006] */
	public static final int		AC_G_S_U_POOL_IDENTIFIER							= 453;					/* [RFC4006] */
	public static final int		AC_CC_UNIT_TYPE										= 454;					/* [RFC4006] */
	public static final int		AC_MULTIPLE_SERVICES_INDICATOR						= 455;					/* [RFC4006] */
	public static final int		AC_MULTIPLE_SERVICES_CREDIT_CONTROL					= 456;					/* [RFC4006] */
	public static final int		AC_G_S_U_POOL_REFERENCE								= 457;					/* [RFC4006] */
	public static final int		AC_USER_EQUIPMENT_INFO								= 458;					/* [RFC4006] */
	public static final int		AC_USER_EQUIPMENT_INFO_TYPE							= 459;					/* [RFC4006] */
	public static final int		AC_USER_EQUIPMENT_INFO_VALUE						= 460;					/* [RFC4006] */
	public static final int		AC_SERVICE_CONTEXT_ID								= 461;					/* [RFC4006] */
	public static final int		AC_EAP_PAYLOAD										= 462;					/* [RFC4072] */
	public static final int		AC_EAP_REISSUED_PAYLOAD								= 463;					/* [RFC4072] */
	public static final int		AC_EAP_MASTER_SESSION_KEY							= 464;					/* [RFC4072] */
	public static final int		AC_ACCOUNTING_EAP_AUTH_METHOD						= 465;					/* [RFC4072] */
	public static final int		AC_ACCOUNTING_RECORD_TYPE							= 480;					/* [RFC6733] */
	public static final int		AC_ACCOUNTING_REALTIME_REQUIRED						= 483;					/* [RFC6733] */
	public static final int		AC_ACCOUNTING_RECORD_NUMBER							= 485;					/* [RFC6733] */
	public static final int		AC_MIP6_AGENT_INFO									= 486;					/* [RFC5447] */
	public static final int		AC_MIP_CAREOF_ADDRESS								= 487;					/* [RFC5778] */
	public static final int		AC_MIP_AUTHENTICATOR								= 488;					/* [RFC5778] */
	public static final int		AC_MIP_MAC_MOBILITY_DATA							= 489;					/* [RFC5778] */
	public static final int		AC_MIP_TIMESTAMP									= 490;					/* [RFC5778] */
	public static final int		AC_MIP_MN_HA_SPI									= 491;					/* [RFC5778] */
	public static final int		AC_MIP_MN_HA_MSA									= 492;					/* [RFC5778] */
	public static final int		AC_SERVICE_SELECTION								= 493;					/* [RFC5778] */
	public static final int		AC_MIP6_AUTH_MODE									= 494;					/* [RFC5778] */
	public static final int		AC_TMOD_1											= 495;					/* [RFC5624] */
	public static final int		AC_TOKEN_RATE										= 496;					/* [RFC5624] */
	public static final int		AC_BUCKET_DEPTH										= 497;					/* [RFC5624] */
	public static final int		AC_PEAK_TRAFFIC_RATE								= 498;					/* [RFC5624] */
	public static final int		AC_MINIMUM_POLICED_UNIT								= 499;					/* [RFC5624] */
	public static final int		AC_MAXIMUM_PACKET_SIZE								= 500;					/* [RFC5624] */
	public static final int		AC_TMOD_2											= 501;					/* [RFC5624] */
	public static final int		AC_BANDWITH											= 502;					/* [RFC5624] */
	public static final int		AC_PHB_CLASS										= 503;					/* [RFC5624] */
	public static final int		AC_PMIP6_DHCP_SERVER_ADDRESS						= 504;					/* [RFC5779] */
	public static final int		AC_PMIP6_IPV4_HOME_ADDRESS							= 505;					/* [RFC5779] */
	public static final int		AC_MOBILE_NODE_IDENTIFIER							= 506;					/* [RFC5779] */
	public static final int		AC_SERVICE_CONFIGURATION							= 507;					/* [RFC5779] */
	public static final int		AC_QoS_RESOURCES									= 508;					/* [RFC5777] */
	public static final int		AC_FILTER_RULE										= 509;					/* [RFC5777] */
	public static final int		AC_FILTER_RULE_PRECEDENCE							= 510;					/* [RFC5777] */
	public static final int		AC_CLASSIFIER										= 511;					/* [RFC5777] */
	public static final int		AC_CLASSIFIER_ID									= 512;					/* [RFC5777] */
	public static final int		AC_PROTOCOL											= 513;					/* [RFC5777] */
	public static final int		AC_DIRECTION										= 514;					/* [RFC5777] */
	public static final int		AC_FROM_SPEC										= 515;					/* [RFC5777] */
	public static final int		AC_TO_SPEC											= 516;					/* [RFC5777] */
	public static final int		AC_NEGATED											= 517;					/* [RFC5777] */
	public static final int		AC_IP_ADDRESS										= 518;					/* [RFC5777] */
	public static final int		AC_IP_ADDRESS_RANGE									= 519;					/* [RFC5777] */
	public static final int		AC_IP_ADDRESS_START									= 520;					/* [RFC5777] */
	public static final int		AC_IP_ADDRESS_END									= 521;					/* [RFC5777] */
	public static final int		AC_IP_ADDRESS_MASK									= 522;					/* [RFC5777] */
	public static final int		AC_IP_MASK_BIT_MASK_WIDTH							= 523;					/* [RFC5777] */
	public static final int		AC_MAC_ADDRESS										= 524;					/* [RFC5777] */
	public static final int		AC_MAC_ADDRESS_MASK									= 525;					/* [RFC5777] */
	public static final int		AC_MAC_ADDRESS_MASK_PATTERN							= 526;					/* [RFC5777] */
	public static final int		AC_EUI64_ADDRESS									= 527;					/* [RFC5777] */
	public static final int		AC_EUI64_ADDRESS_MASK								= 528;					/* [RFC5777] */
	public static final int		AC_EUI64_ADDRESS_MASK_PATTERN						= 529;					/* [RFC5777] */
	public static final int		AC_PORT												= 530;					/* [RFC5777] */
	public static final int		AC_PORT_RANGE										= 531;					/* [RFC5777] */
	public static final int		AC_PORT_START										= 532;					/* [RFC5777] */
	public static final int		AC_PORT_END											= 533;					/* [RFC5777] */
	public static final int		AC_USE_ASSIGNED_ADDRESS								= 534;					/* [RFC5777] */
	public static final int		AC_DIFFSERV_CODE_POINT								= 535;					/* [RFC5777] */
	public static final int		AC_FRAGMENTATION_FLAG								= 536;					/* [RFC5777] */
	public static final int		AC_IP_OPTION										= 537;					/* [RFC5777] */
	public static final int		AC_IP_OPTION_TYPE									= 538;					/* [RFC5777] */
	public static final int		AC_IP_OPTION_VALUE									= 539;					/* [RFC5777] */
	public static final int		AC_TCP_OPTION										= 540;					/* [RFC5777] */
	public static final int		AC_TCP_OPTION_TYPE									= 541;					/* [RFC5777] */
	public static final int		AC_TCP_OPTION_VALUE									= 542;					/* [RFC5777] */
	public static final int		AC_TCP_FLAGS										= 543;					/* [RFC5777] */
	public static final int		AC_TCP_FLAG_TYPE									= 544;					/* [RFC5777] */
	public static final int		AC_ICMP_TYPE										= 545;					/* [RFC5777] */
	public static final int		AC_ICMP_TYPE_NUMBER									= 546;					/* [RFC5777] */
	public static final int		AC_ICMP_CODE										= 547;					/* [RFC5777] */
	public static final int		AC_ETH_OPTION										= 548;					/* [RFC5777] */
	public static final int		AC_ETH_PROTO_TYPE									= 549;					/* [RFC5777] */
	public static final int		AC_ETH_ETHER_TYPE									= 550;					/* [RFC5777] */
	public static final int		AC_ETH_SAP											= 551;					/* [RFC5777] */
	public static final int		AC_VLAN_ID_RANGE									= 552;					/* [RFC5777] */
	public static final int		AC_S_VID_START										= 553;					/* [RFC5777] */
	public static final int		AC_S_VID_END										= 554;					/* [RFC5777] */
	public static final int		AC_C_VID_START										= 555;					/* [RFC5777] */
	public static final int		AC_C_VID_END										= 556;					/* [RFC5777] */
	public static final int		AC_USER_PRIORITY_RANGE								= 557;					/* [RFC5777] */
	public static final int		AC_LOW_USER_PRIORITY								= 558;					/* [RFC5777] */
	public static final int		AC_HIGH_USER_PRIORITY								= 559;					/* [RFC5777] */
	public static final int		AC_TIME_OF_DAY_CONDITION							= 560;					/* [RFC5777] */
	public static final int		AC_TIME_OF_DAY_START								= 561;					/* [RFC5777] */
	public static final int		AC_TIME_OF_DAY_END									= 562;					/* [RFC5777] */
	public static final int		AC_DAY_OF_WEEK_MASK									= 563;					/* [RFC5777] */
	public static final int		AC_DAY_OF_MONTH_MASK								= 564;					/* [RFC5777] */
	public static final int		AC_MONTH_OF_YEAR_MASK								= 565;					/* [RFC5777] */
	public static final int		AC_ABSOLUTE_START_TIME								= 566;					/* [RFC5777] */
	public static final int		AC_ABSOLUTE_START_FRACTIONAL_SECONDS				= 567;					/* [RFC5777] */
	public static final int		AC_ABSOLUTE_END_TIME								= 568;					/* [RFC5777] */
	public static final int		AC_ABSOLUTE_END_FRACTIONAL_SECONDS					= 569;					/* [RFC5777] */
	public static final int		AC_TIMEZONE_FLAG									= 570;					/* [RFC5777] */
	public static final int		AC_TIMEZONE_OFFSET									= 571;					/* [RFC5777] */
	public static final int		AC_TREATMENT_ACTION									= 572;					/* [RFC5777] */
	public static final int		AC_QoS_PROFILE_ID									= 573;					/* [RFC5777] */
	public static final int		AC_QoS_PROFILE_TEMPLATE								= 574;					/* [RFC5777] */
	public static final int		AC_QoS_SEMANTICS									= 575;					/* [RFC5777] */
	public static final int		AC_QoS_PARAMETERS									= 576;					/* [RFC5777] */
	public static final int		AC_EXCESS_TREATMENT									= 577;					/* [RFC5777] */
	public static final int		AC_QoS_CAPABILITY									= 578;					/* [RFC5777] */
	public static final int		AC_QoS_AUTHORIZATION_DATA							= 579;					/* [RFC5866] */
	public static final int		AC_BOUND_AUTH_SESSION_ID							= 580;					/* [RFC5866] */
	public static final int		AC_KEY												= 581;					/* [RFC6734] */
	public static final int		AC_KEY_TYPE											= 582;					/* [RFC6734] */
	public static final int		AC_KEYING_MATERIAL									= 583;					/* [RFC6734] */
	public static final int		AC_KEY_LIFETIME										= 584;					/* [RFC6734] */
	public static final int		AC_KEY_SPI											= 585;					/* [RFC6734] */
	public static final int		AC_KEY_NAME											= 586;					/* [RFC6734] */
	public static final int		AC_IKEv2_NONCES										= 587;					/* [RFC6738] */
	public static final int		AC_NI												= 588;					/* [RFC6738] */
	public static final int		AC_NR												= 589;					/* [RFC6738] */
	public static final int		AC_IKEv2_IDENTITY									= 590;					/* [RFC6738] */
	public static final int		AC_INITIATOR_IDENTITY								= 591;					/* [RFC6738] */
	public static final int		AC_ID_TYPE											= 592;					/* [RFC6738] */
	public static final int		AC_IDENTIFICATION_DATA								= 593;					/* [RFC6738] */
	public static final int		AC_RESPONDER_IDENTITY								= 594;					/* [RFC6738] */
	public static final int		AC_NC_REQUEST_TYPE									= 595;					/* [RFC6736] */
	public static final int		AC_NAT_CONTROL_INSTALL								= 596;					/* [RFC6736] */
	public static final int		AC_NAT_CONTROL_REMOVE								= 597;					/* [RFC6736] */
	public static final int		AC_NAT_CONTROL_DEFINITION							= 598;					/* [RFC6736] */
	public static final int		AC_NAT_INTERNAL_ADDRESS								= 599;					/* [RFC6736] */
	public static final int		AC_NAT_EXTERNAL_ADDRESS								= 600;					/* [RFC6736] */
	public static final int		AC_MAX_NAT_BINDINGS									= 601;					/* [RFC6736] */
	public static final int		AC_NAT_CONTROL_BINDING_TEMPLATE						= 602;					/* [RFC6736] */
	public static final int		AC_DUPLICATE_SESSION_ID								= 603;					/* [RFC6736] */
	public static final int		AC_NAT_EXTERNAL_PORT_STYLE							= 604;					/* [RFC6736] */
	public static final int		AC_NAT_CONTROL_RECORD								= 605;					/* [RFC6736] */
	public static final int		AC_NAT_CONTROL_BINDING_STATUS						= 606;					/* [RFC6736] */
	public static final int		AC_CURRENT_NAT_BINDINGS								= 607;					/* [RFC6736] */
	public static final int		AC_DUAL_PRIORITY									= 608;					/* [RFC6735] */
	public static final int		AC_PREEMPTION_PRIORITY								= 609;					/* [RFC6735] */
	public static final int		AC_DEFENDING_PRIORITY								= 610;					/* [RFC6735] */
	public static final int		AC_ADMISSION_PRIORITY								= 611;					/* [RFC6735] */
	public static final int		AC_SIP_RESOURCE_PRIORITY							= 612;					/* [RFC6735] */
	public static final int		AC_SIP_RESOURCE_PRIORITY_NAMESPACE					= 613;					/* [RFC6735] */
	public static final int		AC_SIP_RESOURCE_PRIORITY_VALUE						= 614;					/* [RFC6735] */
	public static final int		AC_APPLICATION_LEVEL_RESOURCE_PRIORITY				= 615;					/* [RFC6735] */
	public static final int		AC_ALRP_NAMESPACE									= 616;					/* [RFC6735] */
	public static final int		AC_ALRP_VALUE										= 617;					/* [RFC6735] */
	public static final int		AC_ERP_RK_REQUEST									= 618;					/* [RFC6942] */
	public static final int		AC_ERP_REALM										= 619;					/* [RFC6942] */
	public static final int		AC_REDIRECT_REALM									= 620;					/* [RFC7075] */
	public static final int		AC_OC_SUPPORTED_FEATURES							= 621;					/* [RFC7683] */
	public static final int		AC_OC_FEATURE_VECTOR								= 622;					/* [RFC7683] */
	public static final int		AC_OC_OLR											= 623;					/* [RFC7683] */
	public static final int		AC_OC_SEQUENCE_NUMBER								= 624;					/* [RFC7683] */
	public static final int		AC_OC_VALIDITY_DURATION								= 625;					/* [RFC7683] */
	public static final int		AC_OC_REPORT_TYPE									= 626;					/* [RFC7683] */
	public static final int		AC_OC_REDUCTION_PERCENTAGE							= 627;					/* [RFC7683] */
	public static final int		AC_ECN_IP_CODEPOINT									= 628;					/* [RFC7660] */
	public static final int		AC_CONGESTION_TREATMENT								= 629;					/* [RFC7660] */
	public static final int		AC_FLOW_COUNT										= 630;					/* [RFC7660] */
	public static final int		AC_PACKET_COUNT										= 631;					/* [RFC7660] */
	public static final int		AC_IP_PREFIX_LENGTH									= 632;					/* [RFC7678] */
	public static final int		AC_BORDER_ROUTER_NAME								= 633;					/* [RFC7678] */
	public static final int		AC_64_MULTICAST_ATTRIBUTES							= 634;					/* [RFC7678] */
	public static final int		AC_ASM_MPREFIX64									= 635;					/* [RFC7678] */
	public static final int		AC_SSM_MPREFIX64									= 636;					/* [RFC7678] */
	public static final int		AC_TUNNEL_SOURCE_PREF_OR_ADDR						= 637;					/* [RFC7678] */
	public static final int		AC_TUNNEL_SOURCE_IPv6_ADDRESS						= 638;					/* [RFC7678] */
	public static final int		AC_PORT_SET_IDENTIFIER								= 639;					/* [RFC7678] */
	public static final int		AC_LW4O6_BINDING									= 640;					/* [RFC7678] */
	public static final int		AC_LW4O6_EXTERNAL_IPv4_ADDR							= 641;					/* [RFC7678] */
	public static final int		AC_MAP_E_ATTRIBUTES									= 642;					/* [RFC7678] */
	public static final int		AC_MAP_MESH_MODE									= 643;					/* [RFC7678] */
	public static final int		AC_MAP_MAPPING_RULE									= 644;					/* [RFC7678] */
	public static final int		AC_RULE_IPv4_ADDR_OR_PREFIX							= 645;					/* [RFC7678] */
	public static final int		AC_RULE_IPv6_PREFIX									= 646;					/* [RFC7678] */
	public static final int		AC_EA_FIELD_LENGTH									= 647;					/* [RFC7678] */
	public static final int		AC_OC_PEER_ALGO										= 648;					/* [RFCIetfDimeAgentOverload11] */
	public static final int		AC_SOURCEID											= 649;					/* [RFCIetfDimeAgentOverload11] */
	public static final int		AC_LOAD												= 650;					/* [RFCIetfDimeLoad09] */
	public static final int		AC_LOAD_TYPE										= 651;					/* [RFCIetfDimeLoad09] */
	public static final int		AC_LOAD_VALUE										= 652;					/* [RFCIetfDimeLoad09] */

	/* Redirect-Host-Usage AVP Values (code 261) */
	public static final int		RHU_DONT_CACHE										= 0;					/* [RFC6733] */
	public static final int		RHU_ALL_SESSION										= 1;					/* [RFC6733] */
	public static final int		RHU_ALL_REALM										= 2;					/* [RFC6733] */
	public static final int		RHU_REALM_AND_APPLICATION							= 3;					/* [RFC6733] */
	public static final int		RHU_ALL_APPLICATION									= 4;					/* [RFC6733] */
	public static final int		RHU_ALL_HOST										= 5;					/* [RFC6733] */
	public static final int		RHU_ALL_USER										= 6;					/* [RFC6733] */

	/* Result-Code AVP Values (code 268) - Informational */
	public static final int		RESULT_DIAMETER_MULTI_ROUND_AUTH					= 1001;					/* [RFC6733] */

	/* Result-Code AVP Values (code 268) - Success */
	public static final int		RC_DIAMETER_SUCCESS									= 2001;					/* [RFC6733] */
	public static final int		RC_DIAMETER_LIMITED_SUCCESS							= 2002;					/* [RFC6733] */
	public static final int		RC_DIAMETER_FIRST_REGISTRATION						= 2003;					/* [RFC4740] */
	public static final int		RC_DIAMETER_SUBSEQUENT_REGISTRATION					= 2004;					/* [RFC4740] */
	public static final int		RC_DIAMETER_UNREGISTERED_SERVICE					= 2005;					/* [RFC4740] */
	public static final int		RC_DIAMETER_SUCCESS_SERVER_NAME_NOT_STORED			= 2006;					/* [RFC4740] */
	public static final int		RC_DIAMETER_SERVER_SELECTION						= 2007;					/* [RFC4740] */
	public static final int		RC_DIAMETER_SUCCESS_AUTH_SENT_SERVER_NOT_STORED		= 2008;					/* [RFC4740] */
	public static final int		RC_DIAMETER_SUCCESS_RELOCATE_HA						= 2009;					/* [RFC5778] */

	/* Result-Code AVP Values (code 268) - Protocol Errors */
	public static final int		RC_DIAMETER_COMMAND_UNSUPPORTED						= 3001;					/* [RFC6733] */
	public static final int		RC_DIAMETER_UNABLE_TO_DELIVER						= 3002;					/* [RFC6733] */
	public static final int		RC_DIAMETER_REALM_NOT_SERVED						= 3003;					/* [RFC6733] */
	public static final int		RC_DIAMETER_DIAMETER_TOO_BUSY						= 3004;					/* [RFC6733] */
	public static final int		RC_DIAMETER_LOOP_DETECTED							= 3005;					/* [RFC6733] */
	public static final int		RC_DIAMETER_REDIRECT_INDICATION						= 3006;					/* [RFC6733] */
	public static final int		RC_DIAMETER_APPLICATION_UNSUPPORTED					= 3007;					/* [RFC6733] */
	public static final int		RC_DIAMETER_INVALID_HDR_BITS						= 3008;					/* [RFC6733] */
	public static final int		RC_DIAMETER_INVALID_AVP_BITS						= 3009;					/* [RFC6733] */
	public static final int		RC_DIAMETER_UNKNOWN_PEER							= 3010;					/* [RFC6733] */
	public static final int		RC_DIAMETER_REALM_REDIRECT_INDICATION				= 3011;					/* [RFC7075] */

	/* Result-Code AVP Values (code 268) - Transient Failures */
	public static final int		RC_DIAMETER_AUTHENTICATION_REJECTED					= 4001;					/* [RFC6733] */
	public static final int		RC_DIAMETER_OUT_OF_SPACE							= 4002;					/* [RFC6733] */
	public static final int		RC_ELECTION_LOST									= 4003;					/* [RFC6733] */
	public static final int		RC_DIAMETER_ERROR_MIP_REPLY_FAILURE					= 4005;					/* [RFC4004] */
	public static final int		RC_DIAMETER_ERROR_HA_NOT_AVAILABLE					= 4006;					/* [RFC4004] */
	public static final int		RC_DIAMETER_ERROR_BAD_KEY							= 4007;					/* [RFC4004] */
	public static final int		RC_DIAMETER_ERROR_MIP_FILTER_NOT_SUPPORTED			= 4008;					/* [RFC4004] */
	public static final int		RC_DIAMETER_END_USER_SERVICE_DENIED					= 4010;					/* [RFC4006] */
	public static final int		RC_DIAMETER_CREDIT_CONTROL_NOT_APPLICABLE			= 4011;					/* [RFC4006] */
	public static final int		RC_DIAMETER_CREDIT_LIMIT_REACHED					= 4012;					/* [RFC4006] */
	public static final int		RC_DIAMETER_USER_NAME_REQUIRED						= 4013;					/* [RFC4740] */
	public static final int		RC_DIAMETER_RESOURCE_FAILURE						= 4014;					/* [RFC6736] */

	/* Result-Code AVP Values (code 268) - Permanent Failure */
	public static final int		RC_DIAMETER_AVP_UNSUPPORTED							= 5001;					/* [RFC6733] */
	public static final int		RC_DIAMETER_UNKNOWN_SESSION_ID						= 5002;					/* [RFC6733] */
	public static final int		RC_DIAMETER_AUTHORIZATION_REJECTED					= 5003;					/* [RFC6733] */
	public static final int		RC_DIAMETER_INVALID_AVP_VALUE						= 5004;					/* [RFC6733] */
	public static final int		RC_DIAMETER_MISSING_AVP								= 5005;					/* [RFC6733] */
	public static final int		RC_DIAMETER_RESOURCES_EXCEEDED						= 5006;					/* [RFC6733] */
	public static final int		RC_DIAMETER_CONTRADICTING_AVPS						= 5007;					/* [RFC6733] */
	public static final int		RC_DIAMETER_AVP_NOT_ALLOWED							= 5008;					/* [RFC6733] */
	public static final int		RC_DIAMETER_AVP_OCCURS_TOO_MANY_TIMES				= 5009;					/* [RFC6733] */
	public static final int		RC_DIAMETER_NO_COMMON_APPLICATION					= 5010;					/* [RFC6733] */
	public static final int		RC_DIAMETER_UNSUPPORTED_VERSION						= 5011;					/* [RFC6733] */
	public static final int		RC_DIAMETER_UNABLE_TO_COMPLY						= 5012;					/* [RFC6733] */
	public static final int		RC_DIAMETER_INVALID_BIT_IN_HEADER					= 5013;					/* [RFC6733] */
	public static final int		RC_DIAMETER_INVALID_AVP_LENGTH						= 5014;					/* [RFC6733] */
	public static final int		RC_DIAMETER_INVALID_MESSAGE_LENGTH					= 5015;					/* [RFC6733] */
	public static final int		RC_DIAMETER_INVALID_AVP_BIT_COMBO					= 5016;					/* [RFC6733] */
	public static final int		RC_DIAMETER_NO_COMMON_SECURITY						= 5017;					/* [RFC6733] */
	public static final int		RC_DIAMETER_RADIUS_AVP_UNTRANSLATABLE				= 5018;					/* [RFC4849] */
	public static final int		RC_DIAMETER_ERROR_NO_FOREIGN_HA_SERVICE				= 5024;					/* [RFC4004] */
	public static final int		RC_DIAMETER_ERROR_END_TO_END_MIP_KEY_ENCRYPTION		= 5025;					/* [RFC4004] */
	public static final int		RC_DIAMETER_USER_UNKNOWN							= 5030;					/* [RFC4006] */
	public static final int		RC_DIAMETER_RATING_FAILED							= 5031;					/* [RFC4006] */
	public static final int		RC_DIAMETER_ERROR_USER_UNKNOWN						= 5032;					/* [RFC4740] */
	public static final int		RC_DIAMETER_ERROR_IDENTITIES_DONT_MATCH				= 5033;					/* [RFC4740] */
	public static final int		RC_DIAMETER_ERROR_IDENTITY_NOT_REGISTERED			= 5034;					/* [RFC4740] */
	public static final int		RC_DIAMETER_ERROR_ROAMING_NOT_ALLOWED				= 5035;					/* [RFC4740] */
	public static final int		RC_DIAMETER_ERROR_IDENTITY_ALREADY_REGISTERED		= 5036;					/* [RFC4740] */
	public static final int		RC_DIAMETER_ERROR_AUTH_SCHEME_NOT_SUPPORTED			= 5037;					/* [RFC4740] */
	public static final int		RC_DIAMETER_ERROR_IN_ASSIGNMENT_TYPE				= 5038;					/* [RFC4740] */
	public static final int		RC_DIAMETER_ERROR_TOO_MUCH_DATA						= 5039;					/* [RFC4740] */
	public static final int		RC_DIAMETER_ERROR_NOT_SUPPORTED_USER_DATA			= 5040;					/* [RFC4740] */
	public static final int		RC_DIAMETER_ERROR_MIP6_AUTH_MODE					= 5041;					/* [RFC5778] */
	public static final int		RC_DIAMETER_UNKNOWN_BINDING_TEMPLATE_NAME			= 5042;					/* [RFC6736] */
	public static final int		RC_DIAMETER_BINDING_FAILURE							= 5043;					/* [RFC6736] */
	public static final int		RC_DIAMETER_MAX_BINDING_SET_FAILURE					= 5044;					/* [RFC6736] */
	public static final int		RC_DIAMETER_MAXIMUM_BINDINGS_REACHED_FOR_ENDPOINT	= 5045;					/* [RFC6736] */
	public static final int		RC_DIAMETER_SESSION_EXISTS							= 5046;					/* [RFC6736] */
	public static final int		RC_DIAMETER_INSUFFICIENT_CLASSIFIERS				= 5047;					/* [RFC6736] */
	public static final int		RC_DIAMETER_ERROR_EAP_CODE_UNKNOWN					= 5048;					/* [RFC6942] */

	/* Session-Binding AVP Values (code 270) */
	public static final int		SESSION_BINDING_RE_AUTH								= 1;					/* [RFC6733] */
	public static final int		SESSION_BINDING_STR									= 2;					/* [RFC6733] */
	public static final int		SESSION_BINDING_ACCOUNTING							= 4;					/* [RFC6733] */

	/* Session-Server-Failover AVP Values (code 271) */
	public static final int		SSF_REFUSE_SERVICE									= 0;					/* [RFC6733] */
	public static final int		SSF_TRY_AGAIN										= 1;					/* [RFC6733] */
	public static final int		SSF_ALLOW_SERVICE									= 2;					/* [RFC6733] */
	public static final int		SSF_TRY_AGAIN_ALLOW_SERVICE							= 3;					/* [RFC6733] */

	/* Disconnect-Cause AVP Values (code 273) */
	public static final int		DC_REBOOTING										= 0;					/* [RFC6733] */
	public static final int		DC_BUSY												= 1;					/* [RFC6733] */
	public static final int		DC_DO_NOT_WANT_TO_TALK_TO_YOU						= 2;					/* [RFC6733] */

	/* Auth-Request-Type AVP Values (code 274) */
	public static final int		ART_AUTHENTICATE_ONLY								= 1;					/* [RFC6733] */
	public static final int		ART_AUTHORIZE_ONLY									= 2;					/* [RFC6733] */
	public static final int		ART_AUTHORIZE_AUTHENTICATE							= 3;					/* [RFC6733] */

	/* Auth-Session-State AVP Values (code 277) */
	public static final int		ASS_STATE_MAINTAINED								= 0;					/* [RFC6733] */
	public static final int		ASS_NO_STATE_MAINTAINED								= 1;					/* [RFC6733] */

	/* Re-Auth-Request-Type AVP Values (code 285) */
	public static final int		RART_AUTHORIZE_ONLY									= 0;					/* [RFC6733] */
	public static final int		RART_AUTHORIZE_AUTHENTICATE							= 1;					/* [RFC6733] */

	/* Termination-Cause AVP Values (code 295) */
	public static final int		TC_DIAMETER_LOGOUT									= 1;					/* [RFC3588andRFC6733] */
	public static final int		TC_DIAMETER_SERVICE_NOT_PROVIDED					= 2;					/* [RFC3588andRFC6733] */
	public static final int		TC_DIAMETER_BAD_ANSWER								= 3;					/* [RFC3588andRFC6733] */
	public static final int		TC_DIAMETER_ADMINISTRATIVE							= 4;					/* [RFC3588andRFC6733] */
	public static final int		TC_DIAMETER_LINK_BROKEN								= 5;					/* [RFC3588andRFC6733] */
	public static final int		TC_DIAMETER_AUTH_EXPIRED							= 6;					/* [RFC3588andRFC6733] */
	public static final int		TC_DIAMETER_USER_MOVED								= 7;					/* [RFC3588andRFC6733] */
	public static final int		TC_DIAMETER_SESSION_TIMEOUT							= 8;					/* [RFC3588andRFC6733] */
	public static final int		TC_USER_REQUEST										= 11;					/* [RFC2866andRFC7155] */
	public static final int		TC_LOST_CARRIER										= 12;					/* [RFC2866andRFC7155] */
	public static final int		TC_LOST_SERVICE										= 13;					/* [RFC2866andRFC7155] */
	public static final int		TC_IDLE_TIMEOUT										= 14;					/* [RFC2866andRFC7155] */
	public static final int		TC_SESSION_TIMEOUT									= 15;					/* [RFC2866andRFC7155] */
	public static final int		TC_ADMIN_RESET										= 16;					/* [RFC2866andRFC7155] */
	public static final int		TC_ADMIN_REBOOT										= 17;					/* [RFC2866andRFC7155] */
	public static final int		TC_PORT_ERROR										= 18;					/* [RFC2866andRFC7155] */
	public static final int		TC_NAS_ERROR										= 19;					/* [RFC2866andRFC7155] */
	public static final int		TC_NAS_REQUEST										= 20;					/* [RFC2866andRFC7155] */
	public static final int		TC_NAS_REBOOT										= 21;					/* [RFC2866andRFC7155] */
	public static final int		TC_PORT_UNNEEDED									= 22;					/* [RFC2866andRFC7155] */
	public static final int		TC_PORT_PREEMPTED									= 23;					/* [RFC2866andRFC7155] */
	public static final int		TC_PORT_SUSPENDED									= 24;					/* [RFC2866andRFC7155] */
	public static final int		TC_SERVICE_UNAVAILABLE								= 25;					/* [RFC2866andRFC7155] */
	public static final int		TC_CALLBACK											= 26;					/* [RFC2866andRFC7155] */
	public static final int		TC_USER_ERROR										= 27;					/* [RFC2866andRFC7155] */
	public static final int		TC_HOST_REQUEST										= 28;					/* [RFC2866andRFC7155] */
	public static final int		TC_SUPPLICANT_RESTART								= 29;					/* [RFC3580andRFC7155] */
	public static final int		TC_REAUTHENTICATION_FAILURE							= 30;					/* [RFC3580andRFC7155] */
	public static final int		TC_PORT_REINITIALIZED								= 31;					/* [RFC3580andRFC7155] */
	public static final int		TC_PORT_ADMINISTRATIVELY_DISABLED					= 32;					/* [RFC3580andRFC7155] */

	/* Inband-Security-Id AVP (code 299) */
	public static final int		ISI_NO_INBAND_SECURITY								= 0;					/* [RFC6733] */
	public static final int		ISI_TLS												= 1;					/* [RFC6733] */

	/* MIP-Feature-Vector AVP (code 337) */
	public static final int		MIP_MOBILE_NODE_HOME_ADDRESS_REQUESTED				= 1;					/* [RFC4004] */
	public static final int		MIP_HOME_ADDRESS_ALLOCATABLE_ONLY_IN_HOME_REALM		= 2;					/* [RFC4004] */
	public static final int		MIP_HOME_AGENT_REQUESTED							= 4;					/* [RFC4004] */
	public static final int		MIP_FOREIGN_HOME_AGENT_AVAILABLE					= 8;					/* [RFC4004] */
	public static final int		MIP_MN_HA_KEY_REQUESTED								= 16;					/* [RFC4004] */
	public static final int		MIP_MN_FA_KEY_REQUESTED								= 32;					/* [RFC4004] */
	public static final int		MIP_FA_HA_KEY_REQUESTED								= 64;					/* [RFC4004] */
	public static final int		MIP_HOME_AGENT_IN_FOREIGN_NETWORK					= 128;					/* [RFC4004] */
	public static final int		MIP_CO_LOCATED_MOBILE_NODE							= 256;					/* [RFC4004] */

	/* MIP-Algorithm-Type AVP Values (code 345) */
	public static final int		MIP_HMAC_SHA_1										= 2;					/* [RFC4004] */

	/* MIP-Replay-Mode AVP Values (code 346) */
	public static final int		MIP_NONE											= 1;					/* [RFC4004] */
	public static final int		MIP_TIMESTAMPS										= 2;					/* [RFC4004] */
	public static final int		MIP_NONCES											= 3;					/* [RFC4004] */

	/* SIP-Server-Assignment-Type AVP Values (375) */
	public static final int		SIP_SA_NO_ASSIGNMENT								= 0;					/* [RFC4740] */
	public static final int		SIP_SA_REGISTRATION									= 1;					/* [RFC4740] */
	public static final int		SIP_SA_RE_REGISTRATION								= 2;					/* [RFC4740] */
	public static final int		SIP_SA_UNREGISTERED_USER							= 3;					/* [RFC4740] */
	public static final int		SIP_SA_TIMEOUT_DEREGISTRATION						= 4;					/* [RFC4740] */
	public static final int		SIP_SA_USER_DEREGISTRATION							= 5;					/* [RFC4740] */
	public static final int		SIP_SA_TIMEOUT_DEREGISTRATION_STORE_SERVER_NAME		= 6;					/* [RFC4740] */
	public static final int		SIP_SA_USER_DEREGISTRATION_STORE_SERVER_NAME		= 7;					/* [RFC4740] */
	public static final int		SIP_SA_ADMINISTRATIVE_DEREGISTRATION				= 8;					/* [RFC4740] */
	public static final int		SIP_SA_AUTHENTICATION_FAILURE						= 9;					/* [RFC4740] */
	public static final int		SIP_SA_AUTHENTICATION_TIMEOUT						= 10;					/* [RFC4740] */
	public static final int		SIP_SA_DEREGISTRATION_TOO_MUCH_DATA					= 11;					/* [RFC4740] */

	/* SIP-Authentication-Scheme AVP Values (377) */
	public static final int		SIP_DIGEST											= 0;					/* [RFC4740] */

	/* SIP-Reason-Code AVP Values (384) */
	public static final int		SIP_PERMANENT_TERMINATION							= 0;					/* [RFC4740] */
	public static final int		SIP_NEW_SIP_SERVER_ASSIGNED							= 1;					/* [RFC4740] */
	public static final int		SIP_SERVER_CHANGE									= 2;					/* [RFC4740] */
	public static final int		SIP_REMOVE_SIP_SERVER								= 3;					/* [RFC4740] */

	/* SIP-User-Authorization-Type AVP Values (387) */
	public static final int		SIP_UA_REGISTRATION									= 0;					/* [RFC4740] */
	public static final int		SIP_UA_DEREGISTRATION								= 1;					/* [RFC4740] */
	public static final int		SIP_UA_REGISTRATION_AND_CAPABILITIES				= 2;					/* [RFC4740] */

	/* SIP-User-Data-Already-Available AVP Values (392) */
	public static final int		SIP_USER_DATA_NOT_AVAILABLE							= 0;					/* [RFC4740] */
	public static final int		SIP_USER_DATA_ALREADY_AVAILABLE						= 1;					/* [RFC4740] */

	/* Accounting-Auth-Method AVP Values (code 406) */
	public static final int		AAM_PAP												= 1;					/* [RFC7155] */
	public static final int		AAM_CHAP											= 2;					/* [RFC7155] */
	public static final int		AAM_MS_CHAP_1										= 3;					/* [RFC7155] */
	public static final int		AAM_MS_CHAP_2										= 4;					/* [RFC7155] */
	public static final int		AAM_EAP												= 5;					/* [RFC7155] */
	public static final int		AAM_NONE											= 7;					/* [RFC7155] */

	/* Origin-AAA-Protocol AVP Values (code 408) */
	public static final int		ORIGIN_AAA_PROTOCOL_RADIUS							= 1;					/* [RFC7155] */

	/* CC-Request-Type AVP Values (code 416) */
	public static final int		CC_INITIAL_REQUEST									= 1;					/* [RFC4006] */
	public static final int		CC_UPDATE_REQUEST									= 2;					/* [RFC4006] */
	public static final int		CC_TERMINATION_REQUEST								= 3;					/* [RFC4006] */
	public static final int		CC_EVENT_REQUEST									= 4;					/* [RFC4006] */

	/* CC-Session-Failover AVP Values (code 418) */
	public static final int		CC_FAILOVER_NOT_SUPPORTED							= 0;					/* [RFC4006] */
	public static final int		CC_FAILOVER_SUPPORTED								= 1;					/* [RFC4006] */

	/* Check-Balance-Result AVP Values (code 422) */
	public static final int		CHECK_BALANCE_ENOUGH_CREDIT							= 0;					/* [RFC4006] */
	public static final int		CHECK_BALANCE_NO_CREDIT								= 1;					/* [RFC4006] */

	/* Credit-Control AVP Values (code 426) */
	public static final int		CC_CREDIT_AUTHORIZATION								= 0;					/* [RFC4006] */
	public static final int		CC_RE_AUTHORIZATION									= 1;					/* [RFC4006] */

	/* Credit-Control-Failure-Handling AVP Values (code 427) */
	public static final int		CC_TERMINATE										= 0;					/* [RFC4006] */
	public static final int		CC_CONTINUE											= 1;					/* [RFC4006] */
	public static final int		CC_RETRY_AND_TERMINATE								= 2;					/* [RFC4006] */

	/* Direct-Debiting-Failure-Handling AVP Values (code 428) */
	public static final int		DD_TERMINATE_OR_BUFFER								= 0;					/* [RFC4006] */
	public static final int		DD_CONTINUE											= 1;					/* [RFC4006] */

	/* Redirect-Address-Type AVP Values (code 433) */
	public static final int		RAT_IPV4_ADDRESS									= 0;					/* [RFC4006] */
	public static final int		RAT_IPV6_ADDRESS									= 1;					/* [RFC4006] */
	public static final int		RAT_URL												= 2;					/* [RFC4006] */
	public static final int		RAT_SIP_URI											= 3;					/* [RFC4006] */

	/* Requested-Action AVP Values (436) */
	public static final int		RA_DIRECT_DEBITING									= 0;					/* [RFC4006] */
	public static final int		RA_REFUND_ACCOUNT									= 1;					/* [RFC4006] */
	public static final int		RA_CHECK_BALANCE									= 2;					/* [RFC4006] */
	public static final int		RA_PRICE_ENQUIRY									= 3;					/* [RFC4006] */

	/* Final-Unit-Action AVP Values (code 449) */
	public static final int		FUA_TERMINATE										= 0;					/* [RFC4006] */
	public static final int		FUA_REDIRECT										= 1;					/* [RFC4006] */
	public static final int		FUA_RESTRICT_ACCESS									= 2;					/* [RFC4006] */

	/* Subscription-Id-Type AVP Values (code 450) */
	public static final int		SIT_END_USER_EI64									= 0;					/* [RFC4006] */
	public static final int		SIT_END_USER_IMSI									= 1;					/* [RFC4006] */
	public static final int		SIT_END_USER_SIP_URI								= 2;					/* [RFC4006] */
	public static final int		SIT_END_USER_NAI									= 3;					/* [RFC4006] */
	public static final int		SIT_END_USER_PRIVATE								= 4;					/* [RFC4006] */

	/* CC-Unit-Type AVP Values (code 454) */
	public static final int		CC_TIME												= 0;					/* [RFC4006] */
	public static final int		CC_MONEY											= 1;					/* [RFC4006] */
	public static final int		CC_TOTAL_OCTETS										= 2;					/* [RFC4006] */
	public static final int		CC_INPUT_OCTETS										= 3;					/* [RFC4006] */
	public static final int		CC_OUTPUT_OCTETS									= 4;					/* [RFC4006] */
	public static final int		CC_SERVICE_SPECIFIC_UNITS							= 5;					/* [RFC4006] */

	/* Multiple-Services-Indicator AVP Values (code 455) */
	public static final int		MULTIPLE_SERVICES_NOT_SUPPORTED						= 0;					/* [RFC4006] */
	public static final int		MULTIPLE_SERVICES_SUPPORTED							= 1;					/* [RFC4006] */

	/* Tariff-Change-Usage AVP Values (code 452) */
	public static final int		TCU_UNIT_BEFORE_TARIFF_CHANGE						= 0;					/* [RFC4006] */
	public static final int		TCU_UNIT_AFTER_TARIFF_CHANGE						= 1;					/* [RFC4006] */
	public static final int		TCU_UNIT_INDETERMINATE								= 2;					/* [RFC4006] */

	/* User-Equipment-Info-Type AVP Values (code 459) */
	public static final int		UEIT_IMEISV											= 0;					/* [RFC4006] */
	public static final int		UEIT_MAC											= 1;					/* [RFC4006] */
	public static final int		UEIT_EUI64											= 2;					/* [RFC4006] */
	public static final int		UEIT_MODIFIED_EUI64									= 3;					/* [RFC4006] */

	/* Accounting-Record-Type AVP Values (code 480) */
	public static final int		ACCT_EVENT_RECORD									= 1;					/* [RFC6733] */
	public static final int		ACCT_START_RECORD									= 2;					/* [RFC6733] */
	public static final int		ACCT_INTERIM_RECORD									= 3;					/* [RFC6733] */
	public static final int		ACCT_STOP_RECORD									= 4;					/* [RFC6733] */

	/* Accounting-Realtime-Required AVP Values (code 483) */
	public static final int		ACCT_DELIVER_AND_GRANT								= 1;					/* [RFC6733] */
	public static final int		ACCT_GRANT_AND_STORE								= 2;					/* [RFC6733] */
	public static final int		ACCT_GRANT_AND_LOSE									= 3;					/* [RFC6733] */

	/* Treatment-Action AVP Values (code 572) */
	public static final int		TREATMENT_ACTION_DROP								= 0;					/* [RFC5777] */
	public static final int		TREATMENT_ACTION_SHAPE								= 1;					/* [RFC5777] */
	public static final int		TREATMENT_ACTION_MARK								= 2;					/* [RFC5777] */
	public static final int		TREATMENT_ACTION_PERMIT								= 3;					/* [RFC5777] */

	/* QoS-Semantics AVP Values (code 575) */
	public static final int		QoS_DESIRED											= 0;					/* [RFC5777] */
	public static final int		QoS_AVAILABLE										= 1;					/* [RFC5777] */
	public static final int		QoS_DELIVERED										= 2;					/* [RFC5777] */
	public static final int		QoS_MINIMUM_QOS										= 3;					/* [RFC5777] */
	public static final int		QoS_AUTHORIZED										= 4;					/* [RFC5777] */

	/* Key-Type AVP Values (code 582) */
	public static final int		DSRK												= 0;					/* [RFC6734] */
	public static final int		RRK													= 1;					/* [RFC6734] */
	public static final int		RMSK												= 2;					/* [RFC6734] */
	public static final int		IKEv2_SK											= 3;					/* [RFC6738] */

	/* NC-Request-Type AVP Values (code 595) */
	public static final int		NC_INITIAL_REQUEST									= 1;					/* [RFC6736] */
	public static final int		NC_UPDATE_REQUEST									= 2;					/* [RFC6736] */
	public static final int		NC_QUERY_REQUEST									= 3;					/* [RFC6736] */

	/* NAT-External-Port-Style AVP Values (code 604) */
	public static final int		NAT_FOLLOW_INTERNAL_PORT_STYLE						= 1;					/* [RFC6736] */

	/* NAT-Control-Binding-Status (code 606) */
	public static final int		NAT_INITIAL_REQUEST									= 1;					/* [RFC6736] */
	public static final int		NAT_UPDATE_REQUEST									= 2;					/* [RFC6736] */
	public static final int		NAT_QUERY_REQUEST									= 3;					/* [RFC6736] */

	/* OC-Feature-Vector AVP Values (code 622) */
	public static final int		OC_OLR_DEFAULT_ALGO									= 0x0000000000000001;	/* [RFC7683] */

	/* OC-Report-Type AVP Values (code 626) */
	public static final int		OC_HOST_REPORT										= 0;					/* [RFC7683] */
	public static final int		OC_REALM_REPORT										= 1;					/* [RFC7683] */

	/* Known Vendor IDs */
	public static final int		VENDOR_ID_NONE										= 0;
	public static final int		VENDOR_ID_3GPP										= 10415;

	/* Known Application IDs */
	public static final int		APP_ID_DIAMETER_COMMON_MESSAGE						= 0;					/* [RFC6733] */
	public static final int		APP_ID_NASREQ										= 1;					/* [RFC7155] */
	public static final int		APP_ID_MOBILE_IPv4									= 2;					/* [RFC4004] */
	public static final int		APP_ID_DIAMETER_BASE_ACCOUNTING						= 3;					/* [RFC6733] */
	public static final int		APP_ID_DIAMETER_CREDIT_CONTROL						= 4;					/* [RFC4006] */
	public static final int		APP_ID_DIAMETER_EAP									= 5;					/* [RFC4072] */
	public static final int		APP_ID_DAMETER_SIP_APPLICATION						= 6;					/* [RFC4740] */
	public static final int		APP_ID_DIAMETER_MOBILE_IPv6_IKE						= 7;					/* [RFC5778] */
	public static final int		APP_ID_DIAMETER_MOBILE_IPv6_AUTH					= 8;					/* [RFC5778] */
	public static final int		APP_ID_DIAMETER_QOS_APPLICATION						= 9;					/* [RFC5866] */
	public static final int		APP_ID_DIAMETER_CAPABILITIES_UPDATE					= 10;					/* [RFC6737] */
	public static final int		APP_ID_DIAMETER_IKE_SK								= 11;					/* [RFC6738] */
	public static final int		APP_ID_DIAMETER_NAT_CONTROL_APPLICATION				= 12;					/* [RFC6736] */
	public static final int		APP_ID_DIAMETER_ERP									= 13;					/* [RFC6942] */
	public static final int		APP_ID_3GPP_Cx										= 16777216;
	public static final int		APP_ID_3GPP_Sh										= 16777217;
	public static final int		APP_ID_3GPP_Re										= 16777218;
	public static final int		APP_ID_3GPP_Wx										= 16777219;
	public static final int		APP_ID_3GPP_Zn										= 16777220;
	public static final int		APP_ID_3GPP_Zh										= 16777221;
	public static final int		APP_ID_3GPP_Gq										= 16777222;
	public static final int		APP_ID_3GPP_Gmb										= 16777223;
	public static final int		APP_ID_3GPP_Gx										= 16777224;
	public static final int		APP_ID_3GPP_Gx_OVER_Gy								= 16777225;
	public static final int		APP_ID_3GPP_MM10									= 16777226;
	public static final int		APP_ID_3GPP_Sta										= 16777250;
	public static final int		APP_ID_3GPP_S6a										= 16777251;
	public static final int		APP_ID_3GPP_SWm										= 16777264;
	public static final int		APP_ID_3GPP_SWx										= 16777265;
	public static final int		APP_ID_3GPP_Gxx										= 16777266;
	public static final int		APP_ID_3GPP_S9										= 16777267;
	public static final int		APP_ID_3GPP_Zpn										= 16777268;
	public static final int		APP_ID_3GPP_S6b										= 16777272;
	public static final int		APP_ID_3GPP_SLh										= 16777291;
	public static final int		APP_ID_3GPP_SGmb									= 16777292;
	public static final int		APP_ID_3GPP_Sy										= 16777302;
	public static final int		APP_ID_3GPP_Sd										= 16777303;
}
