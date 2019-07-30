package com.biubiu.common.protocol;

public final class IMDto {
	private IMDto() {
	}

	public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {
	}

	public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
		registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
	}

	public interface IMDtoProtocolOrBuilder extends
			// @@protoc_insertion_point(interface_extends:protocol.IMDtoProtocol)
			com.google.protobuf.MessageOrBuilder {

		/**
		 * <code>string userId = 1;</code>
		 */
		java.lang.String getUserId();

		/**
		 * <code>string userId = 1;</code>
		 */
		com.google.protobuf.ByteString getUserIdBytes();

		/**
		 * <code>string username = 2;</code>
		 */
		java.lang.String getUsername();

		/**
		 * <code>string username = 2;</code>
		 */
		com.google.protobuf.ByteString getUsernameBytes();

		/**
		 * <code>string message = 3;</code>
		 */
		java.lang.String getMessage();

		/**
		 * <code>string message = 3;</code>
		 */
		com.google.protobuf.ByteString getMessageBytes();

		/**
		 * <code>int32 type = 4;</code>
		 */
		int getType();

		/**
		 * <code>string ext = 5;</code>
		 */
		java.lang.String getExt();

		/**
		 * <code>string ext = 5;</code>
		 */
		com.google.protobuf.ByteString getExtBytes();

		/**
		 * <code>string timestap = 6;</code>
		 */
		java.lang.String getTimestap();

		/**
		 * <code>string timestap = 6;</code>
		 */
		com.google.protobuf.ByteString getTimestapBytes();
	}

	/**
	 * Protobuf type {@code protocol.IMDtoProtocol}
	 */
	public static final class IMDtoProtocol extends com.google.protobuf.GeneratedMessageV3 implements
			// @@protoc_insertion_point(message_implements:protocol.IMDtoProtocol)
			IMDtoProtocolOrBuilder {
		private static final long serialVersionUID = 0L;

		// Use IMDtoProtocol.newBuilder() to construct.
		private IMDtoProtocol(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
			super(builder);
		}

		private IMDtoProtocol() {
			userId_ = "";
			username_ = "";
			message_ = "";
			ext_ = "";
			timestap_ = "";
		}

		@java.lang.Override
		public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
			return this.unknownFields;
		}

		@SuppressWarnings("unused")
		private IMDtoProtocol(com.google.protobuf.CodedInputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			this();
			if (extensionRegistry == null) {
				throw new java.lang.NullPointerException();
			}
			int mutable_bitField0_ = 0;
			com.google.protobuf.UnknownFieldSet.Builder unknownFields = com.google.protobuf.UnknownFieldSet
					.newBuilder();
			try {
				boolean done = false;
				while (!done) {
					int tag = input.readTag();
					switch (tag) {
					case 0:
						done = true;
						break;
					case 10: {
						java.lang.String s = input.readStringRequireUtf8();

						userId_ = s;
						break;
					}
					case 18: {
						java.lang.String s = input.readStringRequireUtf8();

						username_ = s;
						break;
					}
					case 26: {
						java.lang.String s = input.readStringRequireUtf8();

						message_ = s;
						break;
					}
					case 32: {

						type_ = input.readInt32();
						break;
					}
					case 42: {
						java.lang.String s = input.readStringRequireUtf8();

						ext_ = s;
						break;
					}
					case 50: {
						java.lang.String s = input.readStringRequireUtf8();

						timestap_ = s;
						break;
					}
					default: {
						if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
							done = true;
						}
						break;
					}
					}
				}
			} catch (com.google.protobuf.InvalidProtocolBufferException e) {
				throw e.setUnfinishedMessage(this);
			} catch (java.io.IOException e) {
				throw new com.google.protobuf.InvalidProtocolBufferException(e).setUnfinishedMessage(this);
			} finally {
				this.unknownFields = unknownFields.build();
				makeExtensionsImmutable();
			}
		}

		public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
			return com.biubiu.common.protocol.IMDto.internal_static_protocol_IMDtoProtocol_descriptor;
		}

		@java.lang.Override
		protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
			return com.biubiu.common.protocol.IMDto.internal_static_protocol_IMDtoProtocol_fieldAccessorTable
					.ensureFieldAccessorsInitialized(com.biubiu.common.protocol.IMDto.IMDtoProtocol.class,
							com.biubiu.common.protocol.IMDto.IMDtoProtocol.Builder.class);
		}

		public static final int USERID_FIELD_NUMBER = 1;
		private volatile java.lang.Object userId_;

		/**
		 * <code>string userId = 1;</code>
		 */
		public java.lang.String getUserId() {
			java.lang.Object ref = userId_;
			if (ref instanceof java.lang.String) {
				return (java.lang.String) ref;
			} else {
				com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
				java.lang.String s = bs.toStringUtf8();
				userId_ = s;
				return s;
			}
		}

		/**
		 * <code>string userId = 1;</code>
		 */
		public com.google.protobuf.ByteString getUserIdBytes() {
			java.lang.Object ref = userId_;
			if (ref instanceof java.lang.String) {
				com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
				userId_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}

		public static final int USERNAME_FIELD_NUMBER = 2;
		private volatile java.lang.Object username_;

		/**
		 * <code>string username = 2;</code>
		 */
		public java.lang.String getUsername() {
			java.lang.Object ref = username_;
			if (ref instanceof java.lang.String) {
				return (java.lang.String) ref;
			} else {
				com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
				java.lang.String s = bs.toStringUtf8();
				username_ = s;
				return s;
			}
		}

		/**
		 * <code>string username = 2;</code>
		 */
		public com.google.protobuf.ByteString getUsernameBytes() {
			java.lang.Object ref = username_;
			if (ref instanceof java.lang.String) {
				com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
				username_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}

		public static final int MESSAGE_FIELD_NUMBER = 3;
		private volatile java.lang.Object message_;

		/**
		 * <code>string message = 3;</code>
		 */
		public java.lang.String getMessage() {
			java.lang.Object ref = message_;
			if (ref instanceof java.lang.String) {
				return (java.lang.String) ref;
			} else {
				com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
				java.lang.String s = bs.toStringUtf8();
				message_ = s;
				return s;
			}
		}

		/**
		 * <code>string message = 3;</code>
		 */
		public com.google.protobuf.ByteString getMessageBytes() {
			java.lang.Object ref = message_;
			if (ref instanceof java.lang.String) {
				com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
				message_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}

		public static final int TYPE_FIELD_NUMBER = 4;
		private int type_;

		/**
		 * <code>int32 type = 4;</code>
		 */
		public int getType() {
			return type_;
		}

		public static final int EXT_FIELD_NUMBER = 5;
		private volatile java.lang.Object ext_;

		/**
		 * <code>string ext = 5;</code>
		 */
		public java.lang.String getExt() {
			java.lang.Object ref = ext_;
			if (ref instanceof java.lang.String) {
				return (java.lang.String) ref;
			} else {
				com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
				java.lang.String s = bs.toStringUtf8();
				ext_ = s;
				return s;
			}
		}

		/**
		 * <code>string ext = 5;</code>
		 */
		public com.google.protobuf.ByteString getExtBytes() {
			java.lang.Object ref = ext_;
			if (ref instanceof java.lang.String) {
				com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
				ext_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}

		public static final int TIMESTAP_FIELD_NUMBER = 6;
		private volatile java.lang.Object timestap_;

		/**
		 * <code>string timestap = 6;</code>
		 */
		public java.lang.String getTimestap() {
			java.lang.Object ref = timestap_;
			if (ref instanceof java.lang.String) {
				return (java.lang.String) ref;
			} else {
				com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
				java.lang.String s = bs.toStringUtf8();
				timestap_ = s;
				return s;
			}
		}

		/**
		 * <code>string timestap = 6;</code>
		 */
		public com.google.protobuf.ByteString getTimestapBytes() {
			java.lang.Object ref = timestap_;
			if (ref instanceof java.lang.String) {
				com.google.protobuf.ByteString b = com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
				timestap_ = b;
				return b;
			} else {
				return (com.google.protobuf.ByteString) ref;
			}
		}

		private byte memoizedIsInitialized = -1;

		@java.lang.Override
		public final boolean isInitialized() {
			byte isInitialized = memoizedIsInitialized;
			if (isInitialized == 1)
				return true;
			if (isInitialized == 0)
				return false;

			memoizedIsInitialized = 1;
			return true;
		}

		@java.lang.Override
		public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
			if (!getUserIdBytes().isEmpty()) {
				com.google.protobuf.GeneratedMessageV3.writeString(output, 1, userId_);
			}
			if (!getUsernameBytes().isEmpty()) {
				com.google.protobuf.GeneratedMessageV3.writeString(output, 2, username_);
			}
			if (!getMessageBytes().isEmpty()) {
				com.google.protobuf.GeneratedMessageV3.writeString(output, 3, message_);
			}
			if (type_ != 0) {
				output.writeInt32(4, type_);
			}
			if (!getExtBytes().isEmpty()) {
				com.google.protobuf.GeneratedMessageV3.writeString(output, 5, ext_);
			}
			if (!getTimestapBytes().isEmpty()) {
				com.google.protobuf.GeneratedMessageV3.writeString(output, 6, timestap_);
			}
			unknownFields.writeTo(output);
		}

		@java.lang.Override
		public int getSerializedSize() {
			int size = memoizedSize;
			if (size != -1)
				return size;

			size = 0;
			if (!getUserIdBytes().isEmpty()) {
				size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, userId_);
			}
			if (!getUsernameBytes().isEmpty()) {
				size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, username_);
			}
			if (!getMessageBytes().isEmpty()) {
				size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, message_);
			}
			if (type_ != 0) {
				size += com.google.protobuf.CodedOutputStream.computeInt32Size(4, type_);
			}
			if (!getExtBytes().isEmpty()) {
				size += com.google.protobuf.GeneratedMessageV3.computeStringSize(5, ext_);
			}
			if (!getTimestapBytes().isEmpty()) {
				size += com.google.protobuf.GeneratedMessageV3.computeStringSize(6, timestap_);
			}
			size += unknownFields.getSerializedSize();
			memoizedSize = size;
			return size;
		}

		@java.lang.Override
		public boolean equals(final java.lang.Object obj) {
			if (obj == this) {
				return true;
			}
			if (!(obj instanceof com.biubiu.common.protocol.IMDto.IMDtoProtocol)) {
				return super.equals(obj);
			}
			com.biubiu.common.protocol.IMDto.IMDtoProtocol other = (com.biubiu.common.protocol.IMDto.IMDtoProtocol) obj;

			if (!getUserId().equals(other.getUserId()))
				return false;
			if (!getUsername().equals(other.getUsername()))
				return false;
			if (!getMessage().equals(other.getMessage()))
				return false;
			if (getType() != other.getType())
				return false;
			if (!getExt().equals(other.getExt()))
				return false;
			if (!getTimestap().equals(other.getTimestap()))
				return false;
			if (!unknownFields.equals(other.unknownFields))
				return false;
			return true;
		}

		@SuppressWarnings("unchecked")
		@java.lang.Override
		public int hashCode() {
			if (memoizedHashCode != 0) {
				return memoizedHashCode;
			}
			int hash = 41;
			hash = (19 * hash) + getDescriptor().hashCode();
			hash = (37 * hash) + USERID_FIELD_NUMBER;
			hash = (53 * hash) + getUserId().hashCode();
			hash = (37 * hash) + USERNAME_FIELD_NUMBER;
			hash = (53 * hash) + getUsername().hashCode();
			hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
			hash = (53 * hash) + getMessage().hashCode();
			hash = (37 * hash) + TYPE_FIELD_NUMBER;
			hash = (53 * hash) + getType();
			hash = (37 * hash) + EXT_FIELD_NUMBER;
			hash = (53 * hash) + getExt().hashCode();
			hash = (37 * hash) + TIMESTAP_FIELD_NUMBER;
			hash = (53 * hash) + getTimestap().hashCode();
			hash = (29 * hash) + unknownFields.hashCode();
			memoizedHashCode = hash;
			return hash;
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseFrom(java.nio.ByteBuffer data)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseFrom(java.nio.ByteBuffer data,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data, extensionRegistry);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseFrom(com.google.protobuf.ByteString data)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseFrom(com.google.protobuf.ByteString data,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data, extensionRegistry);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseFrom(byte[] data)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseFrom(byte[] data,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws com.google.protobuf.InvalidProtocolBufferException {
			return PARSER.parseFrom(data, extensionRegistry);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseFrom(java.io.InputStream input)
				throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseFrom(java.io.InputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseDelimitedFrom(java.io.InputStream input)
				throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseDelimitedFrom(java.io.InputStream input,
				com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input,
					extensionRegistry);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseFrom(
				com.google.protobuf.CodedInputStream input) throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol parseFrom(
				com.google.protobuf.CodedInputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
				throws java.io.IOException {
			return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
		}

		@java.lang.Override
		public Builder newBuilderForType() {
			return newBuilder();
		}

		public static Builder newBuilder() {
			return DEFAULT_INSTANCE.toBuilder();
		}

		public static Builder newBuilder(com.biubiu.common.protocol.IMDto.IMDtoProtocol prototype) {
			return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
		}

		@java.lang.Override
		public Builder toBuilder() {
			return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
		}

		@java.lang.Override
		protected Builder newBuilderForType(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
			Builder builder = new Builder(parent);
			return builder;
		}

		/**
		 * Protobuf type {@code protocol.IMDtoProtocol}
		 */
		public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
				// @@protoc_insertion_point(builder_implements:protocol.IMDtoProtocol)
				com.biubiu.common.protocol.IMDto.IMDtoProtocolOrBuilder {
			public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
				return com.biubiu.common.protocol.IMDto.internal_static_protocol_IMDtoProtocol_descriptor;
			}

			@java.lang.Override
			protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
				return com.biubiu.common.protocol.IMDto.internal_static_protocol_IMDtoProtocol_fieldAccessorTable
						.ensureFieldAccessorsInitialized(com.biubiu.common.protocol.IMDto.IMDtoProtocol.class,
								com.biubiu.common.protocol.IMDto.IMDtoProtocol.Builder.class);
			}

			// Construct using com.biubiu.common.protocol.IMDto.IMDtoProtocol.newBuilder()
			private Builder() {
				maybeForceBuilderInitialization();
			}

			private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
				super(parent);
				maybeForceBuilderInitialization();
			}

			private void maybeForceBuilderInitialization() {
				if (com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders) {
				}
			}

			@java.lang.Override
			public Builder clear() {
				super.clear();
				userId_ = "";

				username_ = "";

				message_ = "";

				type_ = 0;

				ext_ = "";

				timestap_ = "";

				return this;
			}

			@java.lang.Override
			public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
				return com.biubiu.common.protocol.IMDto.internal_static_protocol_IMDtoProtocol_descriptor;
			}

			@java.lang.Override
			public com.biubiu.common.protocol.IMDto.IMDtoProtocol getDefaultInstanceForType() {
				return com.biubiu.common.protocol.IMDto.IMDtoProtocol.getDefaultInstance();
			}

			@java.lang.Override
			public com.biubiu.common.protocol.IMDto.IMDtoProtocol build() {
				com.biubiu.common.protocol.IMDto.IMDtoProtocol result = buildPartial();
				if (!result.isInitialized()) {
					throw newUninitializedMessageException(result);
				}
				return result;
			}

			@java.lang.Override
			public com.biubiu.common.protocol.IMDto.IMDtoProtocol buildPartial() {
				com.biubiu.common.protocol.IMDto.IMDtoProtocol result = new com.biubiu.common.protocol.IMDto.IMDtoProtocol(
						this);
				result.userId_ = userId_;
				result.username_ = username_;
				result.message_ = message_;
				result.type_ = type_;
				result.ext_ = ext_;
				result.timestap_ = timestap_;
				onBuilt();
				return result;
			}

			@java.lang.Override
			public Builder clone() {
				return super.clone();
			}

			@java.lang.Override
			public Builder setField(com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
				return super.setField(field, value);
			}

			@java.lang.Override
			public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
				return super.clearField(field);
			}

			@java.lang.Override
			public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
				return super.clearOneof(oneof);
			}

			@java.lang.Override
			public Builder setRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field, int index,
					java.lang.Object value) {
				return super.setRepeatedField(field, index, value);
			}

			@java.lang.Override
			public Builder addRepeatedField(com.google.protobuf.Descriptors.FieldDescriptor field,
					java.lang.Object value) {
				return super.addRepeatedField(field, value);
			}

			@java.lang.Override
			public Builder mergeFrom(com.google.protobuf.Message other) {
				if (other instanceof com.biubiu.common.protocol.IMDto.IMDtoProtocol) {
					return mergeFrom((com.biubiu.common.protocol.IMDto.IMDtoProtocol) other);
				} else {
					super.mergeFrom(other);
					return this;
				}
			}

			public Builder mergeFrom(com.biubiu.common.protocol.IMDto.IMDtoProtocol other) {
				if (other == com.biubiu.common.protocol.IMDto.IMDtoProtocol.getDefaultInstance())
					return this;
				if (!other.getUserId().isEmpty()) {
					userId_ = other.userId_;
					onChanged();
				}
				if (!other.getUsername().isEmpty()) {
					username_ = other.username_;
					onChanged();
				}
				if (!other.getMessage().isEmpty()) {
					message_ = other.message_;
					onChanged();
				}
				if (other.getType() != 0) {
					setType(other.getType());
				}
				if (!other.getExt().isEmpty()) {
					ext_ = other.ext_;
					onChanged();
				}
				if (!other.getTimestap().isEmpty()) {
					timestap_ = other.timestap_;
					onChanged();
				}
				this.mergeUnknownFields(other.unknownFields);
				onChanged();
				return this;
			}

			@java.lang.Override
			public final boolean isInitialized() {
				return true;
			}

			@java.lang.Override
			public Builder mergeFrom(com.google.protobuf.CodedInputStream input,
					com.google.protobuf.ExtensionRegistryLite extensionRegistry) throws java.io.IOException {
				com.biubiu.common.protocol.IMDto.IMDtoProtocol parsedMessage = null;
				try {
					parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
				} catch (com.google.protobuf.InvalidProtocolBufferException e) {
					parsedMessage = (com.biubiu.common.protocol.IMDto.IMDtoProtocol) e.getUnfinishedMessage();
					throw e.unwrapIOException();
				} finally {
					if (parsedMessage != null) {
						mergeFrom(parsedMessage);
					}
				}
				return this;
			}

			private java.lang.Object userId_ = "";

			/**
			 * <code>string userId = 1;</code>
			 */
			public java.lang.String getUserId() {
				java.lang.Object ref = userId_;
				if (!(ref instanceof java.lang.String)) {
					com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
					java.lang.String s = bs.toStringUtf8();
					userId_ = s;
					return s;
				} else {
					return (java.lang.String) ref;
				}
			}

			/**
			 * <code>string userId = 1;</code>
			 */
			public com.google.protobuf.ByteString getUserIdBytes() {
				java.lang.Object ref = userId_;
				if (ref instanceof String) {
					com.google.protobuf.ByteString b = com.google.protobuf.ByteString
							.copyFromUtf8((java.lang.String) ref);
					userId_ = b;
					return b;
				} else {
					return (com.google.protobuf.ByteString) ref;
				}
			}

			/**
			 * <code>string userId = 1;</code>
			 */
			public Builder setUserId(java.lang.String value) {
				if (value == null) {
					throw new NullPointerException();
				}

				userId_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>string userId = 1;</code>
			 */
			public Builder clearUserId() {

				userId_ = getDefaultInstance().getUserId();
				onChanged();
				return this;
			}

			/**
			 * <code>string userId = 1;</code>
			 */
			public Builder setUserIdBytes(com.google.protobuf.ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				checkByteStringIsUtf8(value);

				userId_ = value;
				onChanged();
				return this;
			}

			private java.lang.Object username_ = "";

			/**
			 * <code>string username = 2;</code>
			 */
			public java.lang.String getUsername() {
				java.lang.Object ref = username_;
				if (!(ref instanceof java.lang.String)) {
					com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
					java.lang.String s = bs.toStringUtf8();
					username_ = s;
					return s;
				} else {
					return (java.lang.String) ref;
				}
			}

			/**
			 * <code>string username = 2;</code>
			 */
			public com.google.protobuf.ByteString getUsernameBytes() {
				java.lang.Object ref = username_;
				if (ref instanceof String) {
					com.google.protobuf.ByteString b = com.google.protobuf.ByteString
							.copyFromUtf8((java.lang.String) ref);
					username_ = b;
					return b;
				} else {
					return (com.google.protobuf.ByteString) ref;
				}
			}

			/**
			 * <code>string username = 2;</code>
			 */
			public Builder setUsername(java.lang.String value) {
				if (value == null) {
					throw new NullPointerException();
				}

				username_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>string username = 2;</code>
			 */
			public Builder clearUsername() {

				username_ = getDefaultInstance().getUsername();
				onChanged();
				return this;
			}

			/**
			 * <code>string username = 2;</code>
			 */
			public Builder setUsernameBytes(com.google.protobuf.ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				checkByteStringIsUtf8(value);

				username_ = value;
				onChanged();
				return this;
			}

			private java.lang.Object message_ = "";

			/**
			 * <code>string message = 3;</code>
			 */
			public java.lang.String getMessage() {
				java.lang.Object ref = message_;
				if (!(ref instanceof java.lang.String)) {
					com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
					java.lang.String s = bs.toStringUtf8();
					message_ = s;
					return s;
				} else {
					return (java.lang.String) ref;
				}
			}

			/**
			 * <code>string message = 3;</code>
			 */
			public com.google.protobuf.ByteString getMessageBytes() {
				java.lang.Object ref = message_;
				if (ref instanceof String) {
					com.google.protobuf.ByteString b = com.google.protobuf.ByteString
							.copyFromUtf8((java.lang.String) ref);
					message_ = b;
					return b;
				} else {
					return (com.google.protobuf.ByteString) ref;
				}
			}

			/**
			 * <code>string message = 3;</code>
			 */
			public Builder setMessage(java.lang.String value) {
				if (value == null) {
					throw new NullPointerException();
				}

				message_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>string message = 3;</code>
			 */
			public Builder clearMessage() {

				message_ = getDefaultInstance().getMessage();
				onChanged();
				return this;
			}

			/**
			 * <code>string message = 3;</code>
			 */
			public Builder setMessageBytes(com.google.protobuf.ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				checkByteStringIsUtf8(value);

				message_ = value;
				onChanged();
				return this;
			}

			private int type_;

			/**
			 * <code>int32 type = 4;</code>
			 */
			public int getType() {
				return type_;
			}

			/**
			 * <code>int32 type = 4;</code>
			 */
			public Builder setType(int value) {

				type_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>int32 type = 4;</code>
			 */
			public Builder clearType() {

				type_ = 0;
				onChanged();
				return this;
			}

			private java.lang.Object ext_ = "";

			/**
			 * <code>string ext = 5;</code>
			 */
			public java.lang.String getExt() {
				java.lang.Object ref = ext_;
				if (!(ref instanceof java.lang.String)) {
					com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
					java.lang.String s = bs.toStringUtf8();
					ext_ = s;
					return s;
				} else {
					return (java.lang.String) ref;
				}
			}

			/**
			 * <code>string ext = 5;</code>
			 */
			public com.google.protobuf.ByteString getExtBytes() {
				java.lang.Object ref = ext_;
				if (ref instanceof String) {
					com.google.protobuf.ByteString b = com.google.protobuf.ByteString
							.copyFromUtf8((java.lang.String) ref);
					ext_ = b;
					return b;
				} else {
					return (com.google.protobuf.ByteString) ref;
				}
			}

			/**
			 * <code>string ext = 5;</code>
			 */
			public Builder setExt(java.lang.String value) {
				if (value == null) {
					throw new NullPointerException();
				}

				ext_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>string ext = 5;</code>
			 */
			public Builder clearExt() {

				ext_ = getDefaultInstance().getExt();
				onChanged();
				return this;
			}

			/**
			 * <code>string ext = 5;</code>
			 */
			public Builder setExtBytes(com.google.protobuf.ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				checkByteStringIsUtf8(value);

				ext_ = value;
				onChanged();
				return this;
			}

			private java.lang.Object timestap_ = "";

			/**
			 * <code>string timestap = 6;</code>
			 */
			public java.lang.String getTimestap() {
				java.lang.Object ref = timestap_;
				if (!(ref instanceof java.lang.String)) {
					com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
					java.lang.String s = bs.toStringUtf8();
					timestap_ = s;
					return s;
				} else {
					return (java.lang.String) ref;
				}
			}

			/**
			 * <code>string timestap = 6;</code>
			 */
			public com.google.protobuf.ByteString getTimestapBytes() {
				java.lang.Object ref = timestap_;
				if (ref instanceof String) {
					com.google.protobuf.ByteString b = com.google.protobuf.ByteString
							.copyFromUtf8((java.lang.String) ref);
					timestap_ = b;
					return b;
				} else {
					return (com.google.protobuf.ByteString) ref;
				}
			}

			/**
			 * <code>string timestap = 6;</code>
			 */
			public Builder setTimestap(java.lang.String value) {
				if (value == null) {
					throw new NullPointerException();
				}

				timestap_ = value;
				onChanged();
				return this;
			}

			/**
			 * <code>string timestap = 6;</code>
			 */
			public Builder clearTimestap() {

				timestap_ = getDefaultInstance().getTimestap();
				onChanged();
				return this;
			}

			/**
			 * <code>string timestap = 6;</code>
			 */
			public Builder setTimestapBytes(com.google.protobuf.ByteString value) {
				if (value == null) {
					throw new NullPointerException();
				}
				checkByteStringIsUtf8(value);

				timestap_ = value;
				onChanged();
				return this;
			}

			@java.lang.Override
			public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
				return super.setUnknownFields(unknownFields);
			}

			@java.lang.Override
			public final Builder mergeUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
				return super.mergeUnknownFields(unknownFields);
			}

			// @@protoc_insertion_point(builder_scope:protocol.IMDtoProtocol)
		}

		// @@protoc_insertion_point(class_scope:protocol.IMDtoProtocol)
		private static final com.biubiu.common.protocol.IMDto.IMDtoProtocol DEFAULT_INSTANCE;
		static {
			DEFAULT_INSTANCE = new com.biubiu.common.protocol.IMDto.IMDtoProtocol();
		}

		public static com.biubiu.common.protocol.IMDto.IMDtoProtocol getDefaultInstance() {
			return DEFAULT_INSTANCE;
		}

		private static final com.google.protobuf.Parser<IMDtoProtocol> PARSER = new com.google.protobuf.AbstractParser<IMDtoProtocol>() {
			@java.lang.Override
			public IMDtoProtocol parsePartialFrom(com.google.protobuf.CodedInputStream input,
					com.google.protobuf.ExtensionRegistryLite extensionRegistry)
					throws com.google.protobuf.InvalidProtocolBufferException {
				return new IMDtoProtocol(input, extensionRegistry);
			}
		};

		public static com.google.protobuf.Parser<IMDtoProtocol> parser() {
			return PARSER;
		}

		@java.lang.Override
		public com.google.protobuf.Parser<IMDtoProtocol> getParserForType() {
			return PARSER;
		}

		@java.lang.Override
		public com.biubiu.common.protocol.IMDto.IMDtoProtocol getDefaultInstanceForType() {
			return DEFAULT_INSTANCE;
		}

	}

	private static final com.google.protobuf.Descriptors.Descriptor internal_static_protocol_IMDtoProtocol_descriptor;
	private static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable internal_static_protocol_IMDtoProtocol_fieldAccessorTable;

	public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
		return descriptor;
	}

	private static com.google.protobuf.Descriptors.FileDescriptor descriptor;
	static {
		java.lang.String[] descriptorData = { "\n\020IMDtoProto.proto\022\010protocol\"o\n\rIMDtoPro"
				+ "tocol\022\016\n\006userId\030\001 \001(\t\022\020\n\010username\030\002 \001(\t\022"
				+ "\017\n\007message\030\003 \001(\t\022\014\n\004type\030\004 \001(\005\022\013\n\003ext\030\005 "
				+ "\001(\t\022\020\n\010timestap\030\006 \001(\tB#\n\032com.biubiu.comm"
				+ "on.protocolB\005IMDtob\006proto3" };
		com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner = new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
			public com.google.protobuf.ExtensionRegistry assignDescriptors(
					com.google.protobuf.Descriptors.FileDescriptor root) {
				descriptor = root;
				return null;
			}
		};
		com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData,
				new com.google.protobuf.Descriptors.FileDescriptor[] {}, assigner);
		internal_static_protocol_IMDtoProtocol_descriptor = getDescriptor().getMessageTypes().get(0);
		internal_static_protocol_IMDtoProtocol_fieldAccessorTable = new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
				internal_static_protocol_IMDtoProtocol_descriptor,
				new java.lang.String[] { "UserId", "Username", "Message", "Type", "Ext", "Timestap", });
	}

	// @@protoc_insertion_point(outer_class_scope)
}
